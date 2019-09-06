package it.paolomolteni.farmacobackend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="medicine")
public class Medicine {
	
	/**
	 * 
	 */
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	/**
	 * 
	 */
	@Column(name="date")
	private Date date;
	
	/**
	 * 
	 */
	@Column(name="name")
	private String name;
	
	/**
	 * 
	 */
	@Column(name="description")
	private String description;
	
	/**
	 * 
	 */
	@Column(name="date_expiry")
	private Date dateExpiry;
	
	/**
	 * 
	 */
	@Column(name="date_expiry_when_opened")
	private Date dateExpiryWhenOpened;
	
	/**
	 * 
	 */
	@Column(name="cause")
	private String cause;
	
	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name="id_person", nullable=false)
	private Person person;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateExpiry() {
		return dateExpiry;
	}

	public void setDateExpiry(Date dateExpiry) {
		this.dateExpiry = dateExpiry;
	}

	public Date getDateExpiryWhenOpened() {
		return dateExpiryWhenOpened;
	}

	public void setDateExpiryWhenOpened(Date dateExpiryWhenOpened) {
		this.dateExpiryWhenOpened = dateExpiryWhenOpened;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	

}
