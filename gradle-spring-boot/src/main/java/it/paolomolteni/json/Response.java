package it.paolomolteni.json;

public class Response<T> {

	/**
	 * 
	 */
	private boolean success;
	
	/**
	 * 
	 */
	private T payload;
	
	/**
	 * 
	 */
	public Response() {
		super();
		this.success = true;
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
	 * @param payload
	 */
	public Response(boolean success, T payload) {
		super();
		this.success = success;
		this.payload = payload;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the payload
	 */
	public T getPayload() {
		return payload;
	}

	/**
	 * @param payload the payload to set
	 */
	public void setPayload(T payload) {
		this.payload = payload;
	}
	
}
