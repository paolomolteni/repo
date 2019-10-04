package it.paolomolteni.genericimporter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Field {
	
	/**
	 * 
	 */
	@JsonProperty("excel_name")
	private String excelName;
		
	/**
	 * 
	 */
	@JsonProperty("field_name")
	private String fieldName;
	
	/**
	 * 
	 */
	@JsonProperty("data_type")
	private DataType dataType;
	
	/**
	 * 
	 */
	@JsonProperty("primary_key")
	private boolean primaryKey;
	
	/**
	 * 
	 */
	@JsonProperty("foreign_key")
	private String foreignKey;
	
	/**
	 * 
	 */
	@JsonIgnoreProperties
	private String tableName;

	/**
	 * @return the excelName
	 */
	public String getExcelName() {
		return excelName;
	}

	/**
	 * @param excelName the excelName to set
	 */
	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return the dataType
	 */
	public DataType getDataType() {
		return dataType;
	}

	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	/**
	 * @return the primaryKey
	 */
	public boolean isPrimaryKey() {
		return primaryKey;
	}

	/**
	 * @param primaryKey the primaryKey to set
	 */
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * @return the foreignKey
	 */
	public String getForeignKey() {
		return foreignKey;
	}

	/**
	 * @param foreignKey the foreignKey to set
	 */
	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
	
}
