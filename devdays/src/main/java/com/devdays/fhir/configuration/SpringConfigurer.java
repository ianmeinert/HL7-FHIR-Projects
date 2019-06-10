package com.devdays.fhir.configuration;

import org.springframework.context.annotation.Configuration;

import ca.uhn.fhir.context.FhirContext;

/**
 * This is the Spring configuration class. Note the only real purpose here is to
 * initialize and keep the FHIR context alive, supporting HAPI's statement that
 * the context is resource intensive and should be reused as much as possible.
 * 
 * @author Ian Meinert
 *
 */
@Configuration
public class SpringConfigurer {

	/**
	 * This is the R4 FHIR context that is kept open throughout the applications
	 * lifetime.
	 */
	protected static final FhirContext CONTEXT = FhirContext.forR4();

	/**
	 * The getter for the context.
	 * 
	 * @return {@link FhirContext}
	 */
	public static FhirContext getContext() {
		// increase timeouts since the server might be powered down
		// see http://hapifhir.io/doc_rest_client_http_config.html
		CONTEXT.getRestfulClientFactory().setConnectTimeout(60 * 1000);
		CONTEXT.getRestfulClientFactory().setSocketTimeout(60 * 1000);
		
		return CONTEXT;
	}

}
