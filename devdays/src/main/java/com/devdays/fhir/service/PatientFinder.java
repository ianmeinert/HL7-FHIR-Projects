package com.devdays.fhir.service;

import com.devdays.fhir.configuration.Constants;
import com.devdays.fhir.configuration.SpringConfigurer;
import com.devdays.fhir.model.Patient;
import com.devdays.fhir.utilities.DateUtility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The PatientFinder class contains the CRUD for the patient.
 * 
 * @author Ian Meinert
 *
 */
public class PatientFinder implements IFinder {

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
	 */
	public String find(String id) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		org.hl7.fhir.r4.model.Patient fp = context.readPatient(id);

		Patient p = new Patient();

		p.setGivenName(fp.getNameFirstRep().getGivenAsSingleString());
		p.setFamilyName(fp.getNameFirstRep().getFamily());
		p.setId(fp.getIdElement().getId());
		p.setDob(DateUtility.asLocalDate(fp.getBirthDate()));

		return gson.toJson(p);
	}

	public void createPatient(Patient patient) {
		org.hl7.fhir.r4.model.Patient fp = new org.hl7.fhir.r4.model.Patient();

		// ..populate the patient object..
		fp.setActive(Boolean.TRUE);
		fp.addIdentifier().setSystem("urn:system").setValue(patient.getId());
		fp.addName().addGiven(patient.getGivenName()).setFamily(patient.getFamilyName());
		fp.setBirthDate(DateUtility.asDate(patient.getDob()));
		
		this.context.createPatient(fp);

	}

}
