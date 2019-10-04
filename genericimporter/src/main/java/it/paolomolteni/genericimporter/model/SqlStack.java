package it.paolomolteni.genericimporter.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlStack {
	
	/**
	 * 
	 */
	private List<Sql> queue;
	
	/**
	 * 
	 */
	private Map<String, Table> mapConfiguration;

	/**
	 * @return the queue
	 */
	public List<Sql> getQueue() {
		if(queue == null) {
			queue = new ArrayList<Sql>();
		}
		return queue;
	}

	/**
	 * @param queue the queue to set
	 */
	public void setQueue(List<Sql> queue) {
		this.queue = queue;
	}
	
	/**
	 * @param sqlStr
	 */
	public void addInQueue(Sql sql) {
		getQueue().add(sql);
	}

	/**
	 * @return the mapConfiguration
	 */
	public Map<String, Table> getMapConfiguration() {
		return mapConfiguration;
	}

	/**
	 * @param mapConfiguration the mapConfiguration to set
	 */
	public void setMapConfiguration(Map<String, Table> mapConfiguration) {
		this.mapConfiguration = mapConfiguration;
	}
	

}
