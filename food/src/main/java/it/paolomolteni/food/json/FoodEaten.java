package it.paolomolteni.food.json;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodEaten {
	
	/**
	 * 
	 */
	@JsonProperty("id")
	private Long id;
	
	/**
	 * 
	 */
	@JsonProperty("idPerson")
	private Long idPerson;
	
	/**
	 * 
	 */
	@JsonProperty("meal")
	private String meal;
	
	/**
	 * 
	 */
	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private Date date;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the idPerson
	 */
	public Long getIdPerson() {
		return idPerson;
	}

	/**
	 * @param idPerson the idPerson to set
	 */
	public void setIdPerson(Long idPerson) {
		this.idPerson = idPerson;
	}

	/**
	 * @return the meal
	 */
	public String getMeal() {
		return meal;
	}

	/**
	 * @param meal the meal to set
	 */
	public void setMeal(String meal) {
		this.meal = meal;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
