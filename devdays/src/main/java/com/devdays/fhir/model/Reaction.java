package com.devdays.fhir.model;

import org.hl7.fhir.r4.model.AllergyIntolerance.AllergyIntoleranceReactionComponent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class represents an interpretation of the FHIR
 * {@link AllergyIntoleranceReactionComponent} resource.
 * 
 * @author Ian Meinert
 *
 */
public class Reaction {

	/**
	 * The reaction manifestation.
	 */
	private String manifestation;

	/**
	 * The description of the reaction.
	 */
	private String description;

	/**
	 * The reaction severity.
	 */
	private String severity;

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}

	public String getManifestation() {
		return manifestation;
	}

	public void setManifestation(String manifestation) {
		this.manifestation = manifestation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}
}
