package it.paolomolteni.food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.paolomolteni.food.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {
	
	/**
	 * @param name
	 * @param lastName
	 * @return
	 */
	@Query("SELECT p "
			+ " FROM Person p "
			+ " where 1 = 1 "
			+ " and LOWER(p.name) like :name "
			+ " and LOWER(p.lastName) like :lastName ")
    public List<Person> find(@Param("name") String name, @Param("lastName") String lastName);
	
	/**
	 * @param id
	 * @return
	 */
	@Query("SELECT p "
			+ " FROM Person p "
			+ " where 1 = 1 "
			+ " and p.id = :id")
    public Person findById(@Param("id") Long id);

}
