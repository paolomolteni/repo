package it.paolomolteni.farmacobackend.json;

public class Medicine {
	
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
	public String name;
	
	/**
	 * 
	 */
	public String description;
	
	/**
	 * 
	 */
	public String dateExpiry;
	
	/**
	 * 
	 */
	public String dateExpiryWhenOpened;
	
	/**
	 * 
	 */
	public String cause;
	
	/**
	 * 
	 */
	public long personId;

	/**
	 * @param date
	 * @param name
	 * @param description
	 * @param dateExpiry
	 * @param dateExpiryWhenOpened
	 * @param cause
	 */
	public Medicine(String date, String name, String description, String dateExpiry, String dateExpiryWhenOpened, String cause) {
		super();
		this.date = date;
		this.name = name;
		this.description = description;
		this.dateExpiry = dateExpiry;
		this.dateExpiryWhenOpened = dateExpiryWhenOpened;
		this.cause = cause;
	}

	/**
	 * @param id
	 * @param date
	 * @param name
	 * @param description
	 * @param dateExpiry
	 * @param dateExpiryWhenOpened
	 * @param cause
	 */
	public Medicine(Long id, String date, String name, String description, String dateExpiry, String dateExpiryWhenOpened, String cause) {
		super();
		this.id = id;
		this.date = date;
		this.name = name;
		this.description = description;
		this.dateExpiry = dateExpiry;
		this.dateExpiryWhenOpened = dateExpiryWhenOpened;
		this.cause = cause;
	}
	
	/**
	 * 
	 */
	public Medicine() {
		super();
	}

}
