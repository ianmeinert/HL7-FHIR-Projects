package com.devdays.fhir.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		Patient results = finder.find(id);

		if (results.equals(new Patient())) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<String>(results.toString(), HttpStatus.OK);
	}

	/**
	 * Creates a new patient from posted {@link Patient} object.
	 * 
	 * @param patient   the {@link Patient}
	 * @param ucBuilder a UriComponentsBuilder
	 */
	@PostMapping()
	public ResponseEntity<Void> createPatient(@RequestBody Patient patient, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Patient: " + patient.getFullName());
		PatientFinder finder = new PatientFinder();

		finder.createPatient(patient);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	/**
	 * Updates an existing patient from posted {@link Patient} object.
	 * 
	 * @param id      String path variable
	 * @param patient {@link Patient} model
	 * @return String in Json format
	 */
	@PutMapping(value = Constants.ENTRY_PATH_PATIENT_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Patient> updatePatient(@PathVariable String id, @RequestBody Patient patient) {
		System.out.println("Updating Patient: " + patient.getFullName());
		PatientFinder finder = new PatientFinder();

		Patient currentPatient = finder.find(id);

		if (currentPatient.equals(new Patient())) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
		}

		finder.updatePatient(patient);

		return new ResponseEntity<Patient>(patient, HttpStatus.ACCEPTED);
	}
}