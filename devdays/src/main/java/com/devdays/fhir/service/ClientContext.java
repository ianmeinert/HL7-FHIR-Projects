package com.devdays.fhir.service;

import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.AllergyIntolerance;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;

import com.devdays.fhir.configuration.ClientInterceptor;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.rest.api.MethodOutcome;
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
		return client.read()
				.resource(Patient.class)
				.withId(id)
				.execute();
	}

	public String createPatient(Patient patient) {

		// Invoke the server create method (and send pretty-printed JSON encoding to the
		// server instead of the default which is non-pretty printed XML)
		MethodOutcome outcome = client.create()
				.resource(patient)
				.execute();

		// log the outcome using the ClientInterceptor
		this.setLoggingInterceptor(outcome);

		// This will return Boolean.TRUE if the server responded with an HTTP 201
		// created, otherwise it will return null.
		return outcome.getId().getIdPart();
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
				.include(new Include("AllergyIntolerance:asserter"))
				.include(new Include("AllergyIntolerance:patient"))
				.include(new Include("AllergyIntolerance:recorder"))
				.returnBundle(Bundle.class)
				.execute();

		return bundle;
	}

	public void updatePatient(Patient fp) {
		// Invoke the server update method
		MethodOutcome outcome = client.update()
				.resource(fp)
				.execute();

		IIdType id = outcome.getId();
		System.out.println("Updated ID: " + id.getValue());

	}

	/**
	 * This method will set the FHIR logging interceptor
	 * 
	 * @param outcome the {@link MethodOutcome}
	 */
	private void setLoggingInterceptor(MethodOutcome outcome) {
		ClientInterceptor interceptor = new ClientInterceptor();
		interceptor.interceptResponse(outcome);
	}
}
