package it.paolomolteni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.paolomolteni.model.Person;
import it.paolomolteni.repository.PersonRepository;

@Service
public class PersonService {
	
	/**
	 * 
	 */
	@Autowired
	private PersonRepository personRepository;

	/**
	 * @param id
	 * @return
	 */
	public Person getPerson(int id) {
		return personRepository.findById(id).orElse(null);
	}
	
	/**
	 * @param person
	 * @return
	 */
	public Person save(Person person){
		return personRepository.save(person);
	}	
	
}
