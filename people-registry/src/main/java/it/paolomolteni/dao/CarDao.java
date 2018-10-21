package it.paolomolteni.dao;

import it.paolomolteni.pojo.Car;
import it.paolomolteni.pojo.Person;

public interface CarDao {
	
	public Car createCar(String plate, Person person);

}
