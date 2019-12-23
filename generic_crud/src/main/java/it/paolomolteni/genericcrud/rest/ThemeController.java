package it.paolomolteni.genericcrud.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.paolomolteni.genericcrud.model.Film;
import it.paolomolteni.genericcrud.model.Theme;
import it.paolomolteni.genericcrud.repository.FilmRepository;
import it.paolomolteni.genericcrud.repository.ThemeRepository;

@RestController
@RequestMapping("/theme")
public class ThemeController extends GenericController<it.paolomolteni.genericcrud.json.Theme, Theme> {

	/**
	 * 
	 */
	public ThemeController() {
		super(Theme.class, it.paolomolteni.genericcrud.json.Theme.class);
	}

	/**
	 * 
	 */
	@Autowired
	private ThemeRepository repository;
	
	/**
	 * 
	 */
	@Autowired
	private FilmRepository filmRepository;
	
	/**
	 *
	 */
	@Override
	public CrudRepository<Theme, Long> getRepository() {
		return repository;
	}

	/**
	 *
	 */
	@Override
	public Theme mapJsonToModel(it.paolomolteni.genericcrud.json.Theme json) {
		Theme theme = new Theme();
		theme.setId(json.getId());
		theme.setDescription(json.getDescription());
		
		Film film = filmRepository.findById(json.getIdFilm()).orElse(null);
		theme.setFilm(film);
		
		return theme;
	}

	/**
	 *
	 */
	@Override
	public it.paolomolteni.genericcrud.json.Theme mapModelToJson(Theme model) {
		it.paolomolteni.genericcrud.json.Theme theme = new it.paolomolteni.genericcrud.json.Theme();
		theme.setId(model.getId());
		theme.setDescription(model.getDescription());
		theme.setIdFilm(model.getFilm() != null ? model.getFilm().getId() : null);
		return theme;
	}


}
