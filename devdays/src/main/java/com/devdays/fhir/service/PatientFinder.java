package com.devdays.fhir.service;

import java.time.LocalDate;

import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Period;

import com.devdays.fhir.configuration.Constants;
import com.devdays.fhir.configuration.SpringConfigurer;
import com.devdays.fhir.model.Patient;
import com.devdays.fhir.utilities.DateUtility;

/**
 * The PatientFinder class contains the CRUD for the patient.
 * 
 * @author Ian Meinert
 *
 */
public class PatientFinder implements IFinder<Patient> {

	/**
	 * The {@link ClientContext}.
	 */
	private ClientContext context;

	/**
	 * The default constructor which initializes the {@link ClientContext} and
	 * creates the client connection.
	 */
	public PatientFinder() {
		this.context = new ClientContext(SpringConfigurer.getContext());
		context.createClient(Constants.CLIENT_RESTFUL_URL);
	}

	/**
	 * This method translates the client patient read into the applications internal
	 * patient model.
	 * 
	 * @return {@link Patient} patient
	 */
	public Patient find(String id) {
		org.hl7.fhir.r4.model.Patient fp = context.readPatient(id);

		Patient p = new Patient();

		if (fp != null) {
			for (HumanName name : fp.getName()) {
				// we want the current name. It may be an open period or missing
				if ((name.hasPeriod() && !name.getPeriod().hasEnd()) || !name.hasPeriod()) {
					p.setGivenName(name.getGivenAsSingleString());
					p.setFamilyName(name.getFamily());

					continue;
				}
			}

			p.setId(fp.getIdElement().getId());
			p.setDob(DateUtility.asLocalDate(fp.getBirthDate()));
		}
		return p;
	}

	/**
	 * This method creates a new {@link org.hl7.fhir.r4.model.Patient}.
	 * 
	 * @param patient the {@link Patient}
	 */
	public void createPatient(Patient patient) {
		org.hl7.fhir.r4.model.Patient fp = new org.hl7.fhir.r4.model.Patient();

		// ..populate the patient object.
		fp.setActive(Boolean.TRUE);
		fp.addIdentifier().setSystem("urn:system").setValue(Constants.SYSTEM_ID);
		fp.addName().addGiven(patient.getGivenName()).setFamily(patient.getFamilyName());
		fp.setBirthDate(DateUtility.asDate(patient.getDob()));

		// The resulting id
		String id = this.context.createPatient(fp);

		patient.setId(id);
	}

	public void updatePatient(Patient patient) {
		org.hl7.fhir.r4.model.Patient fp = context.readPatient(patient.getId());

		if (!fp.hasIdentifier()) {
			fp.addIdentifier().setSystem("urn:system").setValue(Constants.SYSTEM_ID);
		}

		fp.setActive(Boolean.TRUE);
		fp.setBirthDate(DateUtility.asDate(patient.getDob()));

		for (HumanName name : fp.getName()) {
			if (!name.hasPeriod() || !name.getPeriod().hasEnd()) {
				Period prd = new Period();
				prd.setEnd(DateUtility.asDate(LocalDate.now()));
				name.setPeriod(prd);
			}
		}

		Period startPrd = new Period();
		startPrd.setStart(DateUtility.asDate(LocalDate.now()));
		fp.addName().addGiven(patient.getGivenName()).setFamily(patient.getFamilyName()).setPeriod(startPrd);

		this.context.updatePatient(fp);
	}
}
