package it.paolomolteni.service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import it.paolomolteni.dao.CarDao;
import it.paolomolteni.dao.PersonDao;
import it.paolomolteni.model.Car;
import it.paolomolteni.model.ReponseBuilder;

@Path("/car")
public class CarService {
	
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
	
	/**
	 * @param personId
	 * @param car
	 * @return
	 */
	@POST
    @Path("/create")
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
