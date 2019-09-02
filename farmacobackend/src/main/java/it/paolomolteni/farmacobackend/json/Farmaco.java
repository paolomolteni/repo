package it.paolomolteni.farmacobackend.json;

public class Farmaco {
	
	/**
	 * 
	 */
	public Long id;
	
	/**
	 * 
	 */
	public String data;
	
	/**
	 * 
	 */
	public String nomeFarmaco;
	
	/**
	 * 
	 */
	public String descrizione;

	/**
	 * @param data
	 * @param nomeFarmaco
	 * @param descrizione
	 */
	public Farmaco(String data, String nomeFarmaco, String descrizione) {
		super();
		this.data = data;
		this.nomeFarmaco = nomeFarmaco;
		this.descrizione = descrizione;
	}
	
	/**
	 * @param id
	 * @param data
	 * @param nomeFarmaco
	 * @param descrizione
	 */
	public Farmaco(Long id, String data, String nomeFarmaco, String descrizione) {
		super();
		this.id = id;
		this.data = data;
		this.nomeFarmaco = nomeFarmaco;
		this.descrizione = descrizione;
	}
	
	/**
	 * 
	 */
	public Farmaco() {
		super();
	}

}
