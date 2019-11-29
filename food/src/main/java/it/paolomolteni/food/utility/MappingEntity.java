package it.paolomolteni.food.utility;

import it.paolomolteni.food.model.Person;
import it.paolomolteni.food.model.PersonStatus;

public class MappingEntity {

	/**
	 * @param person
	 * @return
	 */
	public static it.paolomolteni.food.json.Person mapPerson(Person person){
		it.paolomolteni.food.json.Person model = new it.paolomolteni.food.json.Person();
		model.setId(person.getId());
		model.setName(person.getName());
		model.setLastName(person.getLastName());
		return model;
	}
	
	/**
	 * @param foodEaten
	 * @return
	 */
	public static it.paolomolteni.food.json.FoodEaten mapFoodEaten(it.paolomolteni.food.model.FoodEaten foodEaten){
		it.paolomolteni.food.json.FoodEaten model = new it.paolomolteni.food.json.FoodEaten();
		model.setDate(foodEaten.getDate());
		model.setId(foodEaten.getId());
		model.setIdPerson(foodEaten.getPerson().getId());
		model.setMeal(foodEaten.getMeal());
		return model;
	}
	
	/**
	 * @param personStatus
	 * @return
	 */
	public static it.paolomolteni.food.json.PersonStatus mapPersonStatus(PersonStatus personStatus){
		it.paolomolteni.food.json.PersonStatus model = new it.paolomolteni.food.json.PersonStatus();
		model.setDate(personStatus.getDate());
		model.setDescription(personStatus.getDescription());
		model.setId(personStatus.getId());
		model.setIdPerson(personStatus.getPerson().getId());
		model.setQuality(personStatus.getQuality());
		return model;
	}
	
}
