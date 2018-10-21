package it.paolomolteni.model;

import java.util.List;

/**
 * @author paolo
 *
 */
public class Response<T> {
	
	/**
	 * 
	 */
	public ResponseStatus status;
	
	/**
	 * 
	 */
	public T item;
	
	/**
	 * 
	 */
	public T items;
	
	/**
	 * 
	 */
	public Response() {
		super();
	}
	
	/**
	 * @param status
	 */
	public Response(ResponseStatus status) {
		super();
		this.status = status;
	}

}
