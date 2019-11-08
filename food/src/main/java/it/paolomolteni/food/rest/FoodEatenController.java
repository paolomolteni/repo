package it.paolomolteni.food.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.paolomolteni.food.json.Response;
import it.paolomolteni.food.model.FoodEaten;
import it.paolomolteni.food.model.Person;
import it.paolomolteni.food.repository.FoodEatenRepository;
import it.paolomolteni.food.repository.PersonRepository;
import it.paolomolteni.food.service.FoodEatenService;
import it.paolomolteni.food.utility.MappingEntity;

@RestController
public class FoodEatenController {
	
	/**
	 * 
	 */
	@Autowired
	private FoodEatenRepository foodEatenRepository;
	
	/**
	 * 
	 */
	@Autowired
	private FoodEatenService foodEatenService;
	
	/**
	 * 
	 */
	@Autowired
	private PersonRepository personRepository;
	
	/**
	 * @param model
	 * @return
	 */
	@PostMapping("/food/insert")
    public Response<it.paolomolteni.food.json.FoodEaten> saveFoodEaten(@RequestBody it.paolomolteni.food.json.FoodEaten model) {
		
		FoodEaten foodEaten = null;
		
		if(model.getId() != null) {
			foodEaten = foodEatenRepository.findById(model.getId());
			
			if(foodEaten == null) {
				return Response.error(it.paolomolteni.food.json.FoodEaten.class);
			}
		}
		else {
			foodEaten = new FoodEaten();
		}
		
		if(model.getIdPerson() == null) {
			return Response.error(it.paolomolteni.food.json.FoodEaten.class);
		}
		
		Person person = personRepository.findById(model.getIdPerson());
		
		if(person == null) {
			return Response.error(it.paolomolteni.food.json.FoodEaten.class);
		}
		
		
		foodEaten.setDate(model.getDate());
		foodEaten.setMeal(model.getMeal());
		foodEaten.setPerson(person);
		
		foodEaten = foodEatenService.save(foodEaten);
		
		return Response.ok(MappingEntity.mapFoodEaten(foodEaten));
    }
	
	/**
	 * @param name
	 * @param lastName
	 * @return
	 */
	@RequestMapping("/food/get")
    public Response getPerson(@RequestParam(value="idPerson") Long idPerson) {

		Person person = personRepository.findById(idPerson);
		
		if(person == null) {
			return Response.error();
		}
		
		List<FoodEaten> foods = foodEatenRepository.findByPerson(person);
		
		List<it.paolomolteni.food.json.FoodEaten> modelFoods = foods.stream().map(f -> MappingEntity.mapFoodEaten(f)).collect(Collectors.toList());
		
		return Response.ok(modelFoods);
    }
	
	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/food/delete")
    public Response<it.paolomolteni.food.json.FoodEaten> deletePerson(@RequestParam(value="id") Long id) {
		
		FoodEaten foodEaten = foodEatenRepository.findById(id);
		
		if(foodEaten == null) {
			return Response.error(it.paolomolteni.food.json.FoodEaten.class);
		}
		
		foodEatenService.delete(foodEaten);
		
		return Response.ok(it.paolomolteni.food.json.FoodEaten.class);
    }

}
