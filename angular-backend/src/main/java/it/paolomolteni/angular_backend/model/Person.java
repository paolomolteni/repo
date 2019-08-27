package it.paolomolteni.angular_backend.model;

public class Person {

	/**
	 * 
	 */
	private long id;
	
	/**
	 * 
	 */
	private String name;
	
	/**
	 * 
	 */
	private String lastName;
	
	public Person() {
		super();
	}

	public Person(long id, String name, String lastName) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
