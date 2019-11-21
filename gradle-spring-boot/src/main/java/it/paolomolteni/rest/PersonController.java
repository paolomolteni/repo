package it.paolomolteni.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.paolomolteni.json.Person;
import it.paolomolteni.json.Response;
import it.paolomolteni.service.PersonService;
import it.paolomolteni.utils.Mapping;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	/**
	 * 
	 */
	@Autowired
	private PersonService personService;
	
    /**
     * 
     * http://localhost:8080/gradle/person/get?id=1
     * 
     * @return
     * 
     */
    @GetMapping("/get")
    public Response<Person> getPerson(@RequestParam(value="id", required = true) Integer id) {
    	it.paolomolteni.model.Person person = personService.getPerson(id);
    	
    	if(person == null) {
    		return new Response<Person>(false);
    	}
    	
        return new Response<Person>(true, Mapping.mapPerson(person));
    }
    
    /**
     * 
     * http://localhost:8080/gradle/person/save?name=Mario&lastName=Rossi
     * 
     * @param name
     * @param lastName
     * @return
     */
    @RequestMapping("/save")
    public Response<Person> createPerson(@RequestParam(value="name") String name, @RequestParam(value="lastName") String lastName) {
    	it.paolomolteni.model.Person person = new it.paolomolteni.model.Person();
    	person.setLastName(lastName);
    	person.setName(name);
    	person = personService.save(person);
        return new Response<Person>(true, Mapping.mapPerson(person));
    }

}
