package it.paolomolteni.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import it.paolomolteni.dao.CarDao;
import it.paolomolteni.dao.PersonDao;
import it.paolomolteni.model.Car;
import it.paolomolteni.model.Person;
import it.paolomolteni.model.ReponseBuilder;

@Path("/person")
public class PersonService {
	
	/**
	 * 
	 */
	@Inject
	PersonDao personDao;
	
	/**
	 * 
	 */
	@Inject
	CarDao carDao;
	
	@POST
    @Path("/create")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public it.paolomolteni.model.Response crearePerson(Person person) {
//		http://localhost:8080/people-registry/api/person/create
		
		System.out.println(person);
		
		it.paolomolteni.pojo.Person personSaved = personDao.createPerson(person.name, person.lastName, person.age);
		
		System.out.println(String.format("ID person saved: %s", personSaved.getId()));
		
		return ReponseBuilder.getOkResponse();
	}
	
	@GET
    @Path("/get/all")
	@Produces("application/json")
    public it.paolomolteni.model.Response<List<Person>> getPersons() {
		
//		http://localhost:8080/people-registry/api/person/get/all
		
		List<Person> list = new ArrayList<>();
		
		List<it.paolomolteni.pojo.Person> persons = personDao.getAllPersons();
		
		list.addAll(persons.stream().map(person -> new Person(person.getId(), person.getName(), person.getLastName(), person.getAge())).collect(Collectors.toList()));
		
		return ReponseBuilder.getOkResponseList(list);
    }
	
	@GET
    @Path("/get")
	@Produces("application/json")
    public it.paolomolteni.model.Response<Person> getPerson(@QueryParam("id") long id) {
		
		it.paolomolteni.pojo.Person person = personDao.get(id);
		List<Car> cars = person.getCars().stream().map(car -> new Car(car.getId(), car.getPlate())).collect(Collectors.toList());
		
		Person model = new Person(person.getId(), person.getName(), person.getLastName(), person.getAge());
		model.cars = cars;
		
		return ReponseBuilder.getOkResponse(model);
    }
	
	/**
	 * @param personId
	 * @param car
	 * @return
	 */
	@POST
    @Path("/car/create")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public it.paolomolteni.model.Response addCarToPerson(@QueryParam("personId") long personId, Car car) {
		
		it.paolomolteni.pojo.Person person = personDao.get(personId);
		
		it.paolomolteni.pojo.Car carSaved = carDao.createCar(car.plate, person);
		
		System.out.println(String.format("ID car saved: %s", carSaved.getId()));
		
		return ReponseBuilder.getOkResponse();
	}

}
