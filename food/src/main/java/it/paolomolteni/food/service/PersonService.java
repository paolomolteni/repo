package it.paolomolteni.food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.paolomolteni.food.model.Person;
import it.paolomolteni.food.repository.PersonRepository;

@Service
public class PersonService {
	
	/**
	 * 
	 */
	@Autowired
	private PersonRepository personRepository;
	
	/**
	 * @param person
	 * @return
	 */
	public Person save(Person person) {
		return personRepository.save(person);
	}
	
	/**
	 * @param person
	 */
	public void delete(Person person) {
		personRepository.delete(person);
	}

}
