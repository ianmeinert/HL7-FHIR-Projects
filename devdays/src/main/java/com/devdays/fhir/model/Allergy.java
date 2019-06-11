package com.devdays.fhir.model;

import org.hl7.fhir.r4.model.AllergyIntolerance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class represents an interpretation of the FHIR
 * {@link AllergyIntolerance} resource.
 * 
 * @author Ian Meinert
 *
 */
public class Allergy {
	/**
	 * The allergy type.
	 */
	private String type;

	/**
	 * The allergy category.
	 */
	private String category;

	/**
	 * The allergy {@link Reaction}.
	 */
	private Reaction reaction;

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Reaction getReaction() {
		return reaction;
	}

	public void setReaction(Reaction reaction) {
		this.reaction = reaction;
	}
}
