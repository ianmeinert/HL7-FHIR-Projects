package com.devdays.fhir.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devdays.fhir.configuration.Constants;
import com.devdays.fhir.service.PatientFinder;

/**
 * The patient controller which enables patient EHR data reads.
 * <p>
 * This maps the RESTful service "/patient" entry point
 * 
 * @author Ian Meinert
 *
 */
@RestController
@RequestMapping(Constants.ENTRY_PATH_PATIENT)
public class PatientController {

	/**
	 * Responds with a Json string representation of a patient.
	 * 
	 * @param id the patient id
	 * @return String in Json format
	 */
	@RequestMapping(Constants.ENTRY_PATH_PATIENT_ID)
	public @ResponseBody String getPatient(@PathVariable String id) {
		PatientFinder finder = new PatientFinder();
		return finder.find(id);
	}
}