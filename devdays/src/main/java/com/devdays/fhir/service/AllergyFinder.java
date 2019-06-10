package com.devdays.fhir.service;

import java.util.ArrayList;
import java.util.Collection;

import org.hl7.fhir.r4.model.AllergyIntolerance;
import org.hl7.fhir.r4.model.AllergyIntolerance.AllergyIntoleranceReactionComponent;
import org.hl7.fhir.r4.model.AllergyIntolerance.AllergyIntoleranceSeverity;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.Coding;

import com.devdays.fhir.configuration.Constants;
import com.devdays.fhir.configuration.SpringConfigurer;
import com.devdays.fhir.model.Allergy;
import com.devdays.fhir.model.Reaction;
import com.devdays.fhir.utilities.StringUtility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The PatientFinder class contains the CRUD for the patient.
 * 
 * @author Ian Meinert
 *
 */
public class AllergyFinder implements IFinder {

	/**
	 * The {@link ClientContext}.
	 */
	private ClientContext context;

	/**
	 * The default constructor which initializes the {@link ClientContext} and
	 * creates the client connection.
	 */
	public AllergyFinder() {
		ClientContext context = new ClientContext(SpringConfigurer.getContext());
		context.createClient(Constants.CLIENT_RESTFUL_URL);
	}

	/**
	 * This method translates the client {@link AllergyIntolerance} search into the
	 * applications internal {@link Allergy} model.
	 */
	public String find(String id) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Bundle bundle = context.searchAllergies(id);

		Collection<Allergy> as = new ArrayList<Allergy>();

		for (BundleEntryComponent item : bundle.getEntry()) {
			if (!item.getResource().getClass().equals(AllergyIntolerance.class)) {
				continue;
			}

			AllergyIntolerance ai = (AllergyIntolerance) item.getResource();
			AllergyIntoleranceReactionComponent air = ai.getReactionFirstRep();

			Allergy a = new Allergy();
			Reaction r = new Reaction();
			a.setCategory(ai.getCategory().get(0).getDisplay());
			a.setType(ai.getType().name());

			// we don't have any guarantees that the FHIR datatypes are returned, so check
			// for nulls before populating.
			if (!air.isEmpty()) {
				r.setDescription(StringUtility.clearNull(air.getDescription()));

				Coding manifestation = air.getManifestationFirstRep().getCodingFirstRep();
				r.setManifestation(StringUtility.clearNull(manifestation.getDisplay()));

				AllergyIntoleranceSeverity severity = air.getSeverity();
				r.setSeverity(severity == null ? new String() : air.getSeverity().getDisplay());
				
				a.setReaction(r);
			}
			as.add(a);
		}

		return gson.toJson(as);
	}

}
