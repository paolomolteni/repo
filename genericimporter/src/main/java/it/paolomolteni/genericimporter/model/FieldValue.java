package it.paolomolteni.genericimporter.model;

import java.util.ArrayList;
import java.util.List;

public class FieldValue {
	

	/**
	 * 
	 */
	private List<String> names;
	
	/**
	 * 
	 */
	private Object value;
	
	/**
	 * 
	 */
	public FieldValue() {
		super();
	}
	
	public FieldValue(List<String> names, Object value) {
		super();
		this.names = names;
		this.value = value;
	}

	/**
	 * @return the names
	 */
	public List<String> getNames() {
		if(names == null) {
			names = new ArrayList<String>();
		}
		return names;
	}

	/**
	 * @param names the names to set
	 */
	public void setNames(List<String> names) {
		this.names = names;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[names=");
		builder.append(names);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
	

}
