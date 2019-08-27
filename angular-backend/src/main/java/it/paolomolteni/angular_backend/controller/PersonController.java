package it.paolomolteni.angular_backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.paolomolteni.angular_backend.model.Person;

@CrossOrigin
@RestController
public class PersonController {
	
	@GetMapping("/get/people")
    public List<Person> getPeople() {
		
		System.out.println("@@@getPeople is called");
		
		return new ArrayList<Person>() {{
			this.add(new Person(1, "Mario", "Rossi"));
			this.add(new Person(2, "Giuseppe", "Bianchi"));
		}};
		
    }

}
