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
	 * 
	 */
	public long tsDate;

	/**
	 * @param id
	 * @param tsDate
	 * @param type
	 * @param reason
	 * @param price
	 * @param personId
	 */
	public MedicalExamination(Long id, long tsDate, String type, String reason, BigDecimal price, long personId) {
		super();
		this.id = id;
		this.tsDate = tsDate;
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
