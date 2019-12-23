package it.paolomolteni.genericcrud.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.paolomolteni.genericcrud.model.Film;
import it.paolomolteni.genericcrud.repository.FilmRepository;

@RestController
@RequestMapping("/film")
public class FilmController extends GenericController<it.paolomolteni.genericcrud.json.Film, Film>{

	/**
	 * 
	 */
	@Autowired
	private FilmRepository repository;
	
	/**
	 * 
	 */
	public FilmController() {
		super(Film.class, it.paolomolteni.genericcrud.json.Film.class);
	}

	/**
	 *
	 */
	@Override
	public CrudRepository<Film, Long> getRepository() {
		return repository;
	}

	/**
	 *
	 */
	@Override
	public Film mapJsonToModel(it.paolomolteni.genericcrud.json.Film json) {
		Film film = new Film();
		film.setId(json.getId());
		film.setTitle(json.getTitle());
		return film;
	}

	/**
	 *
	 */
	@Override
	public it.paolomolteni.genericcrud.json.Film mapModelToJson(Film model) {
		it.paolomolteni.genericcrud.json.Film film = new it.paolomolteni.genericcrud.json.Film();
		film.setId(model.getId());
		film.setTitle(model.getTitle());
		return film;
	}


}
