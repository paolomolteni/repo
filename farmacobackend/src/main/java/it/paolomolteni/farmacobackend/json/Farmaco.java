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
	 * 
	 */
	public String dataScadenza;
	
	/**
	 * 
	 */
	public String dataScadenzaAperto;

	/**
	 * @param data
	 * @param nomeFarmaco
	 * @param descrizione
	 * @param dataScadenza
	 * @param dataScadenzaAperto
	 */
	public Farmaco(String data, String nomeFarmaco, String descrizione, String dataScadenza, String dataScadenzaAperto) {
		super();
		this.data = data;
		this.nomeFarmaco = nomeFarmaco;
		this.descrizione = descrizione;
		this.dataScadenza = dataScadenza;
		this.dataScadenzaAperto = dataScadenzaAperto;
	}
	
	/**
	 * @param id
	 * @param data
	 * @param nomeFarmaco
	 * @param descrizione
	 * @param dataScadenza
	 * @param dataScadenzaAperto
	 */
	public Farmaco(Long id, String data, String nomeFarmaco, String descrizione, String dataScadenza, String dataScadenzaAperto) {
		super();
		this.id = id;
		this.data = data;
		this.nomeFarmaco = nomeFarmaco;
		this.descrizione = descrizione;
		this.dataScadenza = dataScadenza;
		this.dataScadenzaAperto = dataScadenzaAperto;
	}
	
	/**
	 * 
	 */
	public Farmaco() {
		super();
	}

}
