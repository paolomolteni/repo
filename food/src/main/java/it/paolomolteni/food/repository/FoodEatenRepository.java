package it.paolomolteni.food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.paolomolteni.food.model.FoodEaten;
import it.paolomolteni.food.model.Person;

public interface FoodEatenRepository extends CrudRepository<FoodEaten, Integer> {

	/**
	 * @param id
	 * @return
	 */
	@Query("SELECT f "
			+ " FROM FoodEaten f "
			+ " where 1 = 1 "
			+ " and f.id = :id")
    public FoodEaten findById(@Param("id") Long id);
	
	/**
	 * @param person
	 * @return
	 */
	@Query("SELECT f "
			+ " FROM FoodEaten f "
			+ " where 1 = 1 "
			+ " and f.person = :person")
    public List<FoodEaten> findByPerson(@Param("person") Person person);
	
}
