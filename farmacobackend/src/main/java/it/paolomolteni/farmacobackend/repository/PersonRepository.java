package it.paolomolteni.farmacobackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.paolomolteni.farmacobackend.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
	
	/**
	 * @return
	 */
	@Query(value = "SELECT p FROM it.paolomolteni.farmacobackend.model.Person p order by p.id")
	public List<Person> getPeople();

}
