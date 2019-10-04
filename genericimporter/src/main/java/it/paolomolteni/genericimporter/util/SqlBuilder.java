package it.paolomolteni.genericimporter.util;

import java.util.List;

import it.paolomolteni.genericimporter.model.Field;

public class SqlBuilder {
	
	/**
	 * @param table
	 * @param primaryKey
	 * @return
	 */
	public static String createSelectSql(String table, String primaryKey) {
		return String.format("SELECT %s FROM %s WHERE %s = :%s", primaryKey, table, primaryKey, primaryKey);
	}
	
	/**
	 * @param table
	 * @param fields
	 * @return
	 */
	public static String createInsertSql(String table, List<Field> fields) {
		String columns = "";
		String values = "";
		
		for(Field field : fields) {
			columns += field.getFieldName()+",";
			values += String.format(":%s,", field.getFieldName());
		}
		
		columns = columns.substring(0, columns.length()-1);
		values = values.substring(0, values.length()-1);
		
		return String.format("INSERT INTO %s(%s) VALUES(%s)", table, columns, values);
	}
	
	/**
	 * @param table
	 * @param primaryKey
	 * @param fields
	 * @return
	 */
	public static String createUpdateSql(String table, String primaryKey, List<Field> fields) {
		String columns = "";
		String query = null;
		
		for(Field field : fields) {
			
			if(!field.getFieldName().equals(primaryKey)) {
				columns += String.format("%s = :%s,", field.getFieldName(), field.getFieldName());
			}
		}
		
		if(columns.length() > 1) {
			columns = columns.substring(0, columns.length()-1);
			query = String.format("UPDATE %s SET %s WHERE %s = :%s", table, columns, primaryKey, primaryKey);
		}
		
		return query;
	}

}
