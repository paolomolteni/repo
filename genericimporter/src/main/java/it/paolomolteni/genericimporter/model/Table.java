package it.paolomolteni.genericimporter.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Table {
	
	/**
	 * 
	 */
	@JsonProperty("order")
	private int order;
	
	/**
	 * 
	 */
	@JsonProperty("fields")
	private List<Field> fields;

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * @return the fields
	 */
	public List<Field> getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	

}
