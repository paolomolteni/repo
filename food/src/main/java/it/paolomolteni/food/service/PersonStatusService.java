package it.paolomolteni.food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.paolomolteni.food.model.PersonStatus;
import it.paolomolteni.food.repository.PersonStatusRepository;

@Service
public class PersonStatusService {

	/**
	 * 
	 */
	@Autowired
	private PersonStatusRepository personStatusRepository;
	
	/**
	 * @param person
	 * @return
	 */
	public PersonStatus save(PersonStatus person) {
		return personStatusRepository.save(person);
	}
	
	/**
	 * @param person
	 */
	public void delete(PersonStatus person) {
		personStatusRepository.delete(person);
	}
	
}
