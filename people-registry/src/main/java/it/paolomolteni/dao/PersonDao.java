package it.paolomolteni.dao;

import java.util.List;

import it.paolomolteni.pojo.Person;

public interface PersonDao {
	
	/**
	 * @param name
	 * @param lastName
	 * @return
	 */
	public Person createPerson(String name, String lastName, Integer age);
	
	/**
	 * @return
	 */
	public List<Person> getAllPersons();
	
	/**
	 * @param personId
	 * @return
	 */
	public Person get(long personId);

}
