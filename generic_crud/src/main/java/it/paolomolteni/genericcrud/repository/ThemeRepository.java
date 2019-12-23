package it.paolomolteni.genericcrud.repository;

import org.springframework.data.repository.CrudRepository;

import it.paolomolteni.genericcrud.model.Theme;

public interface ThemeRepository extends CrudRepository<Theme, Long> {

}
