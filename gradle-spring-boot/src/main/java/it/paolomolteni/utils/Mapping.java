package it.paolomolteni.utils;

import it.paolomolteni.model.Person;

public class Mapping {

	/**
	 * @param person
	 * @return
	 */
	public static it.paolomolteni.json.Person mapPerson(Person person){
		it.paolomolteni.json.Person model = new it.paolomolteni.json.Person();
		model.setId(person.getId());
		model.setLastName(person.getLastName());
		model.setName(person.getName());
		return model;
	}
	
}
