package it.paolomolteni.genericcrud.repository;

import org.springframework.data.repository.CrudRepository;

import it.paolomolteni.genericcrud.model.Film;

public interface FilmRepository extends CrudRepository<Film, Long> {

}
