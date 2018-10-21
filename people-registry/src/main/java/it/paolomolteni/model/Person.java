package it.paolomolteni.model;

import java.util.List;

/**
 * @author paolo
 *
 */
public class Person {
	
	/**
	 * 
	 */
	public long id;
	
	/**
	 * 
	 */
	public String name;
	
	/**
	 * 
	 */
	public String lastName;
	
	/**
	 * 
	 */
	public Integer age;
	
	/**
	 * 
	 */
	public List<Car> cars;

	/**
	 * 
	 */
	public Person() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param lastName
	 * @param age
	 */
	public Person(long id, String name, String lastName, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
	}
	

}
