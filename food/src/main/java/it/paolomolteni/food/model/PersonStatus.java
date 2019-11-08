package it.paolomolteni.food.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="person_status")
public class PersonStatus {
	
	/**
	 * 
	 */
	public static final int MAX_DESCRIPTION_LENGTH = 255;

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
	@Column(name = "description", length = MAX_DESCRIPTION_LENGTH, nullable = false)
	private String description;
	
	/**
	 * 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_person", nullable = false)
	private Person person;
	
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false)
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
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
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
