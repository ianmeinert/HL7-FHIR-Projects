package com.devdays.fhir.service;

/**
 * The interface used for finding a FHIR resource.
 * 
 * @author Ian Meinert
 *
 */
public interface ICrud<T> {

	/**
	 * The read operation.
	 * 
	 * @param id the resource id.
	 * @return
	 */
	public T read(String id);

	/**
	 * The create operation.
	 * 
	 * @param t type T
	 */
	public void create(T t);

	/**
	 * The update operation.
	 * 
	 * @param t type T
	 */
	public void update(T t);
}
