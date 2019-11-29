package it.paolomolteni.food.json;

public class QualityPerDay {

	/**
	 * 
	 */
	private String date;
	
	/**
	 * 
	 */
	private int quality;
	
	/**
	 * @param date
	 * @param quality
	 */
	public QualityPerDay(String date, int quality) {
		super();
		this.date = date;
		this.quality = quality;
	}
	
	/**
	 * 
	 */
	public QualityPerDay() {
		super();
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the quality
	 */
	public int getQuality() {
		return quality;
	}

	/**
	 * @param quality the quality to set
	 */
	public void setQuality(int quality) {
		this.quality = quality;
	}
	

}
