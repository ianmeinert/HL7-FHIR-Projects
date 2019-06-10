package com.devdays.fhir.service;

/**
 * The interface used for finding a FHIR resource.
 * 
 * @author Ian Meinert
 *
 */
public interface IFinder {

	/**
	 * The read operation.
	 * 
	 * @param id the resource id.
	 * @return
	 */
	public String find(String id);
}
