package com.devdays.fhir.controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devdays.fhir.configuration.Constants;
import com.devdays.fhir.model.Allergy;
import com.devdays.fhir.service.AllergyFinder;

/**
 * The patient controller which enables patient EHR data reads.
 * <p>
 * This maps the RESTful service "/patient" entry point
 * 
 * @author Ian Meinert
 *
 */
@RestController
@RequestMapping(Constants.ENTRY_PATH_ALLERGY)
public class AllergyController {

	/**
	 * Responds with a Json string representation of a patient's allergies.
	 * 
	 * @param id the patient id
	 * @return String in Json format
	 */
	@GetMapping()
	public ResponseEntity<Collection<Allergy>> getPatientAllergies(@RequestParam(name="patient", required=true) String patientId) {
		AllergyFinder finder = new AllergyFinder();
		Collection<Allergy> results = finder.read(patientId);

		if (results.isEmpty()) {
			return new ResponseEntity<Collection<Allergy>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Collection<Allergy>>(results, HttpStatus.OK);
	}
}