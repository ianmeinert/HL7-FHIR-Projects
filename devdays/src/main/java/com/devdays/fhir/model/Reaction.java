package com.devdays.fhir.model;

import org.hl7.fhir.r4.model.AllergyIntolerance.AllergyIntoleranceReactionComponent;

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
