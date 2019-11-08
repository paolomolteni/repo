package it.paolomolteni.food.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="person")
public class Person {

	/**
	 * 
	 */
	public static final int MAX_NAME_LENGTH = 50;
	
	/**
	 * 
	 */
	public static final int MAX_LAST_NAME_LENGTH = MAX_NAME_LENGTH;
	
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique=true, nullable=false)
    private Long id;
	
	/**
	 * 
	 */
	@Column(name = "name", length = MAX_NAME_LENGTH, nullable = false)
	private String name;
	
	/**
	 * 
	 */
	@Column(name = "last_name", length = MAX_LAST_NAME_LENGTH, nullable = false)
	private String lastName;
	
	/**
	 * 
	 */
	@OneToMany(mappedBy="person", orphanRemoval=true, cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
	private List<FoodEaten> foods;
	
	/**
	 * 
	 */
	@OneToMany(mappedBy="person", orphanRemoval=true, cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
	private List<PersonStatus> status;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the foods
	 */
	public List<FoodEaten> getFoods() {
		return foods;
	}

	/**
	 * @param foods the foods to set
	 */
	public void setFoods(List<FoodEaten> foods) {
		this.foods = foods;
	}

	/**
	 * @return the status
	 */
	public List<PersonStatus> getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(List<PersonStatus> status) {
		this.status = status;
	}
	
}
