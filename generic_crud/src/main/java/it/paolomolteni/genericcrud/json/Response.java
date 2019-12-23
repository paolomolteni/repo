package it.paolomolteni.genericcrud.json;

public class Response<T> {

	/**
	 * 
	 */
	private boolean success;
	
	/**
	 * 
	 */
	private T data;

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
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	/**
	 * @param <V>
	 * @param data
	 * @return
	 */
	public static <V> Response<V> ok(V data) {
		Response<V> response = new Response<V>();
		response.setData(data);
		response.setSuccess(true);
		return response;
	}
	
	/**
	 * @return
	 */
	public static <V> Response<V> error(Class<V> claz) {
		Response<V> response = new Response<V>();
		response.setSuccess(false);
		return response;
	}
	
	public static <V> Response<V> ok(Class<V> claz) {
		Response<V> response = new Response<V>();
		response.setSuccess(true);
		return response;
	}
	
	public static Response<?> error() {
		Response<?> response = new Response();
		response.setSuccess(false);
		return response;
	}
	
}
