package com.devdays.fhir.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.devdays.fhir.configuration.Constants;
import com.devdays.fhir.model.Patient;
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
	@GetMapping(value = Constants.ENTRY_PATH_PATIENT_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getPatient(@PathVariable String id) {
		PatientFinder finder = new PatientFinder();
		String results = finder.find(id);

		if (results.isEmpty()) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<String>(results, HttpStatus.OK);
	}

	/**
	 * Creates a new patient from posted {@link Patient} object.
	 * 
	 * @param patient the {@link Patient}
	 * @param ucBuilder a UriComponentsBuilder
	 */
	@PostMapping()
	public ResponseEntity<String> createPatient(@RequestBody Patient patient, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Patient: " + patient.getFullName());
		PatientFinder finder = new PatientFinder();

		finder.createPatient(patient);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri());
		
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
}