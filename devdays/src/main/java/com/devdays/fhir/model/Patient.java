package com.devdays.fhir.model;

import java.time.LocalDate;

import com.devdays.fhir.model.Patient;

/**
 * This class represents an interpretation of the FHIR {@link Patient} resource.
 * 
 * @author Ian Meinert
 *
 */
public class Patient {

	/**
	 * The patient id used for querying FHIR services.
	 */
	private String id;

	/**
	 * The full name of the patient.
	 */
	private String name;

	/**
	 * The patient date of birth.
	 */
	private LocalDate dob;

	public String getId() {
		return id;
	}

	public void setId(String string) {
		this.id = string;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
}
