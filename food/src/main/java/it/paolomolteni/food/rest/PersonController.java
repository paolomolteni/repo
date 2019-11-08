package it.paolomolteni.food.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.paolomolteni.food.json.Response;
import it.paolomolteni.food.model.Person;
import it.paolomolteni.food.repository.PersonRepository;
import it.paolomolteni.food.service.PersonService;
import it.paolomolteni.food.utility.MappingEntity;
import it.paolomolteni.food.utility.Utils;

@RestController
public class PersonController {

	/**
	 * 
	 */
	@Autowired
	private PersonService personService;
	
	/**
	 * 
	 */
	@Autowired
	private PersonRepository personRepository;
	
	/**
	 * http://localhost:8080/food/person/insert
	 * 
	 * @param name
	 * @param lastName
	 * @return
	 */
	@PostMapping("/person/insert")
    public Response<it.paolomolteni.food.json.Person> savePerson(@RequestBody it.paolomolteni.food.json.Person model) {
		
		Person person = null;
		
		if(model.getId() != null) {
			person = personRepository.findById(model.getId());
			
			if(person == null) {
				return Response.error(it.paolomolteni.food.json.Person.class);
			}
		}
		else {
			person = new Person();
		}
		
		person.setName(model.getName());
		person.setLastName(model.getLastName());
		
		person = personService.save(person);
		
		return Response.ok(MappingEntity.mapPerson(person));
    }
	
	/**
	 * @param name
	 * @param lastName
	 * @return
	 */
	@RequestMapping("/person/get")
    public Response<List<it.paolomolteni.food.json.Person>> getPerson(@RequestParam(value="name", required = false) String name, @RequestParam(value="lastName", required = false) String lastName) {

		List<Person> persons = personRepository.find(Utils.getLikeValue(name), Utils.getLikeValue(lastName));
		
		List<it.paolomolteni.food.json.Person> modelPersons = persons.stream().map(p -> MappingEntity.mapPerson(p)).collect(Collectors.toList());
		
		return Response.ok(modelPersons);
    }
	
	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/person/delete")
    public Response<it.paolomolteni.food.json.Person> deletePerson(@RequestParam(value="id") Long id) {
		
		Person person = personRepository.findById(id);
		
		if(person == null) {
			return Response.error(it.paolomolteni.food.json.Person.class);
		}
		
		personService.delete(person);
		
		return Response.ok(it.paolomolteni.food.json.Person.class);
    }
	
}
