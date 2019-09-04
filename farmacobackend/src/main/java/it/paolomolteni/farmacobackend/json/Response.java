package it.paolomolteni.farmacobackend.json;

public class Response<T> {

	/**
	 * 
	 */
	public boolean success;
	
	/**
	 * 
	 */
	public T entity;

	/**
	 * 
	 */
	public Response() {
		super();
	}
	
	/**
	 * @param success
	 */
	public Response(boolean success) {
		super();
		this.success = success;
	}

	/**
	 * @param success
	 * @param entity
	 */
	public Response(boolean success, T entity) {
		super();
		this.success = success;
		this.entity = entity;
	}
	
	
}
