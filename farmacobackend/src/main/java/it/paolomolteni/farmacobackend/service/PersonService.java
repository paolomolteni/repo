package it.paolomolteni.farmacobackend.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.paolomolteni.farmacobackend.model.Person;
import it.paolomolteni.farmacobackend.repository.MedicalExaminationRepository;
import it.paolomolteni.farmacobackend.repository.MedicineRepository;
import it.paolomolteni.farmacobackend.repository.PersonRepository;

@Component
public class PersonService {
	
	/**
	 * 
	 */
	@Autowired
	private PersonRepository personRepository;
	
	/**
	 * 
	 */
	@Autowired
	private MedicineRepository medicineRepository;
	
	/**
	 * 
	 */
	@Autowired
	private MedicalExaminationRepository medicalExaminationRepository;
	
	@Transactional
	public void delete(Person person) {
		medicalExaminationRepository.deleteByPerson(person);
		medicineRepository.deleteByPerson(person);
		personRepository.delete(person);
	}

}
