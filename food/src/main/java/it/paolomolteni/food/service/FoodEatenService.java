package it.paolomolteni.food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.paolomolteni.food.model.FoodEaten;
import it.paolomolteni.food.repository.FoodEatenRepository;

@Service
public class FoodEatenService {
	
	/**
	 * 
	 */
	@Autowired
	private FoodEatenRepository foodEatenRepository;
	
	/**
	 * @param foodEaten
	 * @return
	 */
	public FoodEaten save(FoodEaten foodEaten) {
		return foodEatenRepository.save(foodEaten);
	}
	
	/**
	 * @param foodEaten
	 */
	public void delete(FoodEaten foodEaten) {
		foodEatenRepository.delete(foodEaten);
	}

}
