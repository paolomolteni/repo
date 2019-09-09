package it.paolomolteni.farmacobackend.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="medical_examination")
public class MedicalExamination {
	
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
	@Column(name="type")
	private String type;
	
	/**
	 * 
	 */
	@Column(name="reason")
	private String reason;
	
	/**
	 * 
	 */
	@Column(name="price")
	private BigDecimal price;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
