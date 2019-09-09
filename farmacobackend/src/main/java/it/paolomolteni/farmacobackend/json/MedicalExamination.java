package it.paolomolteni.farmacobackend.json;

import java.math.BigDecimal;

public class MedicalExamination {
	
	/**
	 * 
	 */
	public Long id;
	
	/**
	 * 
	 */
	public String date;
	
	/**
	 * 
	 */
	public String type;
	
	/**
	 * 
	 */
	public String reason;
	
	/**
	 * 
	 */
	public long personId;
	
	/**
	 * 
	 */
	public BigDecimal price;

	/**
	 * @param id
	 * @param date
	 * @param type
	 * @param reason
	 * @param price
	 * @param personId
	 */
	public MedicalExamination(Long id, String date, String type, String reason, BigDecimal price, long personId) {
		super();
		this.id = id;
		this.date = date;
		this.type = type;
		this.reason = reason;
		this.price = price;
		this.personId = personId;
	}
	
	/**
	 * 
	 */
	public MedicalExamination() {
		super();
	}

}
