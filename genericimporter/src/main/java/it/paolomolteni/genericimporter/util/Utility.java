package it.paolomolteni.genericimporter.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

import it.paolomolteni.genericimporter.model.DataType;
import it.paolomolteni.genericimporter.model.Field;
import it.paolomolteni.genericimporter.model.FieldValue;
import it.paolomolteni.genericimporter.model.Table;

public class Utility {

	/**
	 * @param fields
	 * @param table
	 * @return
	 */
	public static Field getPrimaryKey(List<Field> fields, String table) {
		return fields.stream().filter(field -> field.isPrimaryKey()).findFirst().orElse(null);
	}
	
	/**
	 * @param key
	 * @return
	 */
	public static String getTable(String key) {
		return key.split(".")[0];
	}
	
	/**
	 * @param key
	 * @return
	 */
	public static String getField(String key) {
		return key.split(".")[1];
	}
	
	public static boolean hasForeignKey(Field field) {
		return StringUtils.isNotBlank(field.getForeignKey());
	}
	
	/**
	 * @param cell
	 * @param dataType
	 * @return
	 */
	public static Object getCellValue(Cell cell, DataType dataType) {
		
		switch (dataType) {
			case STRING:
				return cell.getStringCellValue();
				
			case NUMERIC:
				return cell.getNumericCellValue();
				
			case BOOLEAN:
				return cell.getBooleanCellValue();
				
			case DATE:
			case DATE_TIME:
				return cell.getDateCellValue();
	
			default:
				return null;
		}
		
	}
	
	/**
	 * @param fieldName
	 * @param tables
	 * @return
	 */
	public static List<Field> findFieldsByName(String fieldName, Map<String, Table> tables) {
		List<Field> fields = new ArrayList<Field>();

		for(Entry<String, Table> entryTable : tables.entrySet()) {
			Field fieldFound = entryTable.getValue().getFields().stream().filter(f -> f.getExcelName().contentEquals(fieldName)).findFirst().orElse(null);
			
			if(fieldFound != null) {
				fieldFound.setTableName(entryTable.getKey());
				fields.add(fieldFound);
			}
		}
		
		return fields;
	}
	
	/**
	 * @param rowValues
	 * @param nameToSearch
	 * @return
	 */
	public static FieldValue findValue(Map<String, FieldValue> rowValues, String nameToSearch) {
		return rowValues.entrySet().stream().filter(entry -> entry.getValue().getNames().contains(nameToSearch)).map(entry -> entry.getValue()).findFirst().orElse(null);
	}
	
	/**
	 * @param table
	 * @param field
	 * @return
	 */
	public static String getKey(String table, String field) {
		return String.format("%s.%s", table, field);
	}
	
}
