package it.paolomolteni.genericimporter.model;

public class Sql {

	private String selectByKey;
	
	private String insert;
	
	private String update;
	
	private String primaryKey;
	
	private String table;
	
	/**
	 * @param selectByKey
	 * @param insert
	 * @param update
	 * @param primaryKey
	 * @param table
	 */
	public Sql(String selectByKey, String insert, String update, String primaryKey, String table) {
		super();
		this.selectByKey = selectByKey;
		this.insert = insert;
		this.update = update;
		this.primaryKey = primaryKey;
		this.table = table;
	}
	
	/**
	 * 
	 */
	public Sql() {
		super();
	}

	/**
	 * @return the selectByKey
	 */
	public String getSelectByKey() {
		return selectByKey;
	}

	/**
	 * @param selectByKey the selectByKey to set
	 */
	public void setSelectByKey(String selectByKey) {
		this.selectByKey = selectByKey;
	}

	/**
	 * @return the insert
	 */
	public String getInsert() {
		return insert;
	}

	/**
	 * @param insert the insert to set
	 */
	public void setInsert(String insert) {
		this.insert = insert;
	}

	/**
	 * @return the update
	 */
	public String getUpdate() {
		return update;
	}

	/**
	 * @param update the update to set
	 */
	public void setUpdate(String update) {
		this.update = update;
	}

	/**
	 * @return the primaryKey
	 */
	public String getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * @param primaryKey the primaryKey to set
	 */
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * @return the table
	 */
	public String getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(String table) {
		this.table = table;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[selectByKey=");
		builder.append(selectByKey);
		builder.append(", insert=");
		builder.append(insert);
		builder.append(", update=");
		builder.append(update);
		builder.append(", primaryKey=");
		builder.append(primaryKey);
		builder.append(", table=");
		builder.append(table);
		builder.append("]");
		return builder.toString();
	}

	
	
}
