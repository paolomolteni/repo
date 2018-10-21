package it.paolomolteni.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.paolomolteni.pojo.Car;
import it.paolomolteni.pojo.Person;

public class CarDaoImpl implements CarDao {
	
	@PersistenceContext(unitName="unit1")
	EntityManager em;

	@Override
	public Car createCar(String plate, Person person) {
		Car car = new Car();
		car.setPlate(plate);
		car.setPerson(person);
		return em.merge(car);
	}

}
