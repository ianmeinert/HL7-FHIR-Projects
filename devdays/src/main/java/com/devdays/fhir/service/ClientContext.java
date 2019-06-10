package com.devdays.fhir.service;

import org.hl7.fhir.r4.model.AllergyIntolerance;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.StringClientParam;

/**
 * This class builds the client context required for communicating with the FHIR
 * server.
 * 
 * @author Ian Meinert
 *
 */
public class ClientContext {

	/**
	 * The {@link FhirContext}.
	 */
	private FhirContext context;

	/**
	 * The FHIR client.
	 */
	private IGenericClient client;

	/**
	 * The default constructor accepting a FhirContext.
	 * 
	 * @param context the FHIR context
	 */
	public ClientContext(FhirContext context) {
		this.context = context;
	}

	/**
	 * This method creates the client connection to the given Url.
	 * 
	 * @param url the Url of the FHIR server.
	 */
	public void createClient(String url) {
		// Create a client
		this.client = context.newRestfulGenericClient(url);

	}

	/**
	 * This method sends a read request to the {@link IGenericClient} for a
	 * {@link Patient} resource.
	 * 
	 * @param id the resource id.
	 * @return {@link Patient}
	 */
	public Patient readPatient(String id) {

		// Read a patient with the given ID
		return client.read().resource(Patient.class).withId(id).execute();
	}

	/**
	 * This method sends a search request to the {@link IGenericClient} for a
	 * {@link AllergyIntolerance} resource.
	 * 
	 * @param id the id of the {@link Patient} resource
	 * @return {@link AllergyIntolerance}
	 */
	public Bundle searchAllergies(String id) {
		// Invoke the client.
		Bundle bundle = client.search().forResource(AllergyIntolerance.class)
				.where(new StringClientParam("_id").matches().value(id))
				// the AllergyIntolerance resource references other resources...request those
				// resources be returned in the bundle for later reference.
				.include(new Include("AllergyIntolerance:asserter")).include(new Include("AllergyIntolerance:patient"))
				.include(new Include("AllergyIntolerance:recorder")).returnBundle(Bundle.class).execute();

		return bundle;
	}
}
