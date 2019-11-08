package it.paolomolteni.food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.paolomolteni.food.model.Person;
import it.paolomolteni.food.model.PersonStatus;

public interface PersonStatusRepository extends CrudRepository<PersonStatus, Integer> {

	/**
	 * @param id
	 * @return
	 */
	@Query("SELECT ps "
			+ " FROM PersonStatus ps "
			+ " where 1 = 1 "
			+ " and ps.id = :id")
    public PersonStatus findById(@Param("id") Long id);
	
	/**
	 * @param person
	 * @return
	 */
	@Query("SELECT ps "
			+ " FROM PersonStatus ps "
			+ " where 1 = 1 "
			+ " and ps.person = :person")
    public List<PersonStatus> findByPerson(@Param("person") Person person);
	
}
