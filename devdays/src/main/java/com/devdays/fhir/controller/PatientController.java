package com.devdays.fhir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devdays.fhir.configuration.Constants;
import com.devdays.fhir.service.AllergyFinder;
import com.devdays.fhir.service.PatientFinder;

/**
 * The patient controller which enables patient EHR data reads.
 * <p>
 * This maps the RESTful service "/patient" entry point
 * 
 * @author Ian Meinert
 *
 */
@Controller
@RequestMapping("/patient")
public class PatientController {

	/**
	 * Responds with a Json string representation of a patient.
	 * 
	 * @param id the patient id
	 * @return String in Json format
	 */
	@RequestMapping(Constants.ENTRY_PATH_ID)
	public @ResponseBody String getPatient(@PathVariable String id) {
		PatientFinder finder = new PatientFinder();
		return finder.find(id);
	}

	/**
	 * Responds with a Json string representation of a patient's allergies.
	 * 
	 * @param id the patient id
	 * @return String in Json format
	 */
	@RequestMapping(Constants.ENTRY_PATH_ALLERGY)
	public @ResponseBody String getPatientAllergies(@PathVariable String id) {
		AllergyFinder finder = new AllergyFinder();
		return finder.find(id);
	}
}