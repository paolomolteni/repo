package it.paolomolteni.farmacobackend.json;

public class Medicine {
	
	/**
	 * 
	 */
	public Long id;
	
	/**
	 * 
	 */
	public long tsDate;
	
	/**
	 * 
	 */
	public String name;
	
	/**
	 * 
	 */
	public String description;
	
	/**
	 * 
	 */
	public Long tsDateExpiry;
	
	/**
	 * 
	 */
	public Long tsDateExpiryWhenOpened;
	
	/**
	 * 
	 */
	public String cause;
	
	/**
	 * 
	 */
	public long personId;


	/**
	 * @param tsDate
	 * @param name
	 * @param description
	 * @param tsDateExpiry
	 * @param tsDateExpiryWhenOpened
	 * @param cause
	 */
	public Medicine(long tsDate, String name, String description, Long tsDateExpiry, Long tsDateExpiryWhenOpened, String cause) {
		super();
		this.tsDate = tsDate;
		this.name = name;
		this.description = description;
		this.tsDateExpiry = tsDateExpiry;
		this.tsDateExpiryWhenOpened = tsDateExpiryWhenOpened;
		this.cause = cause;
	}

	/**
	 * @param id
	 * @param tsDate
	 * @param name
	 * @param description
	 * @param tsDateExpiry
	 * @param tsDateExpiryWhenOpened
	 * @param cause
	 */
	public Medicine(Long id, long tsDate, String name, String description, Long tsDateExpiry, Long tsDateExpiryWhenOpened, String cause) {
		super();
		this.id = id;
		this.tsDate = tsDate;
		this.name = name;
		this.description = description;
		this.tsDateExpiry = tsDateExpiry;
		this.tsDateExpiryWhenOpened = tsDateExpiryWhenOpened;
		this.cause = cause;
	}
	
	/**
	 * 
	 */
	public Medicine() {
		super();
	}

}
