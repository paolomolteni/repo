package it.paolomolteni.farmacobackend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="farmaco")
public class Farmaco {
	
	/**
	 * 
	 */
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	/**
	 * 
	 */
	@Column(name="data")
	private Date data;
	
	/**
	 * 
	 */
	@Column(name="nome_farmaco")
	private String nomeFarmaco;
	
	/**
	 * 
	 */
	@Column(name="descrizione")
	private String descrizione;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNomeFarmaco() {
		return nomeFarmaco;
	}

	public void setNomeFarmaco(String nomeFarmaco) {
		this.nomeFarmaco = nomeFarmaco;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}