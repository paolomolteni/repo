package it.paolomolteni.repository;

import org.springframework.data.repository.CrudRepository;

import it.paolomolteni.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}
