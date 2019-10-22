package it.paolomolteni.service;

import javax.ejb.Stateless;

import it.paolomolteni.model.Person;

@Stateless
public class PersonService {

	public Person savePerson(Person person) {
		person.setId(1L);
		return person;
	}
	
	public Person getTestPerson() {
		return new Person() {{
			this.setName("Paolo");
			this.setLastName("Molteni");
		}};
	}
	
}
