package com.devdays.fhir.configuration;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.api.MethodOutcome;

/**
 * The ClientInterceptor class enables logging of Http requests and responses.
 * 
 * @author Ian Meinert
 *
 */
@Interceptor
public class ClientInterceptor {

	/**
	 * This method intercepts the client request to log the outcome.
	 * 
	 * @param outcome the {@link MethodOutcome}s
	 */
	@Hook(Pointcut.CLIENT_REQUEST)
	public void interceptRequest(MethodOutcome outcome) {
		System.out.println(String.format("Client request: %s", outcome.getResponseHeaders()));
	}
}
