package it.paolomolteni.genericcrud.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Theme implements ICrudModel {
	

	/**
	 * 
	 */
	@JsonProperty("id")
	private Long id;
	
	/**
	 * 
	 */
	@JsonProperty("description")
	private String description;
	
	/**
	 * 
	 */
	@JsonProperty("id_film")
	private long idFilm;

	/**
	 *
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the idFilm
	 */
	public long getIdFilm() {
		return idFilm;
	}

	/**
	 * @param idFilm the idFilm to set
	 */
	public void setIdFilm(long idFilm) {
		this.idFilm = idFilm;
	}

}
