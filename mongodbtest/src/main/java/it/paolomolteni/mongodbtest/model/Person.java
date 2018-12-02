package it.paolomolteni.mongodbtest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
	"name",
	"lastName",
	"test"
	
})
public class Person {
	
	private String name;
	
	private String lastName;
	
	@JsonProperty("test")
	private Long test;

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

	public Long getTest() {
		return test;
	}

	public void setTest(Long test) {
		this.test = test;
	}

	public Person() {
		super();
	}
	
	public Person(String name, String lastName, long test) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.test = test;
	}
	
	

}
