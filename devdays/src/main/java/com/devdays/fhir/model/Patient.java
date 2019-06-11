package com.devdays.fhir.model;

import java.time.LocalDate;

import com.devdays.fhir.model.Patient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
	 * The given name of the patient.
	 */
	private String givenName;
	
	/**
	 * The family name of the patient.
	 */
	private String familyName;

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

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getFullName() {
		return this.getGivenName() + " " + this.getFamilyName();
	}
	
	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}
}
