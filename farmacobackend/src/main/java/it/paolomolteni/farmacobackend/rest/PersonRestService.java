package it.paolomolteni.farmacobackend.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.paolomolteni.farmacobackend.json.Response;
import it.paolomolteni.farmacobackend.model.Person;
import it.paolomolteni.farmacobackend.repository.PersonRepository;
import it.paolomolteni.farmacobackend.service.PersonService;

@CrossOrigin
@RestController
@RequestMapping("/person")
public class PersonRestService {
	
	/**
	 * 
	 */
	@Autowired
	private PersonRepository personRepository;
	
	/**
	 * 
	 */
	@Autowired
	private PersonService personService;
	
	/**
	 * @return
	 */
	@GetMapping(path = "/list")
    public List<it.paolomolteni.farmacobackend.json.Person> getList() {
		List<it.paolomolteni.farmacobackend.json.Person> list = new ArrayList<it.paolomolteni.farmacobackend.json.Person>();
		List<Person> people = personRepository.getPeople();

		for(Person person : people) {
			list.add(mapPeople(person));
		}
		
		return list;
    }
	
	/**
	 * @param person
	 * @return
	 */
	@PostMapping(path = "/save")
	public it.paolomolteni.farmacobackend.json.Person save(@RequestBody it.paolomolteni.farmacobackend.json.Person person) {
		Person model = null;
		
		if(person.id != null) {
			model =	personRepository.findById(person.id).orElse(null);
		}
		
		if(model == null) {
			model = new Person();
		}
		
		model.setName(person.name);
		model.setLastName(person.lastName);
		
		model = personRepository.save(model);
		
		return mapPeople(model);
	}
	
	/**
	 * @param personId
	 * @return
	 */
	@PutMapping(path = "/delete")
	public Response<Void> delete(@RequestParam(value="personId") Long personId) {
		Person model = null;
		
		if(personId == null) {
			return new Response<Void>(false);
		}
		
		model =	personRepository.findById(personId).orElse(null);
		
		if(model == null) {
			return new Response<Void>(false);
		}
		
		personService.delete(model);
		
		return new Response<Void>(true);
	}
	
	/**
	 * @param person
	 * @return
	 */
	private it.paolomolteni.farmacobackend.json.Person mapPeople(Person person){
		it.paolomolteni.farmacobackend.json.Person json = new it.paolomolteni.farmacobackend.json.Person();
		json.id = person.getId();
		json.name = person.getName();
		json.lastName = person.getLastName();
		return json;
	}

}
