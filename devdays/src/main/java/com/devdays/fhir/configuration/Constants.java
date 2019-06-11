package com.devdays.fhir.configuration;

/**
 * This class contains the different constants that are used throughout the
 * application.
 * 
 * @author Ian Meinert
 *
 */
public class Constants {
	
	/**
	 * The patient id used within the RESTful entry point.
	 */
	public static final String ENTRY_PATH_PATIENT = "/patient";
	
	/**
	 * The patient id used within the RESTful entry point.
	 */
	public static final String ENTRY_PATH_PATIENT_ID = "/{id}";
	
	/**
	 * The allergies RESTful entry point.
	 */
	public static final String ENTRY_PATH_ALLERGY = "/allergies";

	/**
	 * This is the public HL7 R4 test server path.
	 */
	public static final String CLIENT_RESTFUL_URL = "http://fhirtest.uhn.ca/baseR4";

	public static final String SYSTEM_ID = "ICU-812";
}
