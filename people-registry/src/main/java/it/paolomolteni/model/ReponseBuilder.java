package it.paolomolteni.model;

import java.util.List;

import javax.ws.rs.core.GenericType;

/**
 * @author paolo
 *
 */
public class ReponseBuilder {
	
	/**
	 * @param payload
	 * @return
	 */
	public static <T> Response<T> getOkResponse(T item) {
		Response<T> response = new Response<T>(ResponseStatus.OK);
		response.item = item;
		
		return response;
	}
	
	public static <T> Response<T> getOkResponseList(T items) {
		Response<T> response = new Response<T>(ResponseStatus.OK);
		response.items = items;
		return response;
	}
	
	public static <T> Response<T> getOkResponse() {
		return new Response<T>(ResponseStatus.OK);
	}

}
