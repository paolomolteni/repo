package it.paolomolteni.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import it.paolomolteni.model.Person;

@Path("tutorial")
public class HelloWorld
{
 
	@Inject
	private PersonService personService;
	
    @GET
    @Path("helloworld")
    public String helloworld() {
    	Person person = personService.getTestPerson();
        return String.format("Ciao, %s %s", person.getName(), person.getLastName());
    }
}
