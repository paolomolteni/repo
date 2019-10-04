package it.paolomolteni.genericimporter.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.paolomolteni.genericimporter.model.Field;
import it.paolomolteni.genericimporter.model.FieldValue;
import it.paolomolteni.genericimporter.model.Sql;
import it.paolomolteni.genericimporter.model.SqlStack;
import it.paolomolteni.genericimporter.model.Table;
import it.paolomolteni.genericimporter.util.SqlBuilder;
import it.paolomolteni.genericimporter.util.Utility;

@Component
public class ImporterService {
	
	/**
	 * 
	 */
	Logger logger = LoggerFactory.getLogger(ImporterService.class);
	
	/**
	 * 
	 */
	@PersistenceContext
    private EntityManager em;
	
//	{
//		"tabella_b": {
//			"order": 0,
//			"fields": [{
//				"excel_name": "excel_2",
//				"field_name": "id",
//				"data_type": "NUMERIC",
//				"primary_key": true
//			}]
//		},
//		"tabella_a": {
//			"order": 1,
//			"fields": [{
//				"excel_name": "excel_1",
//				"field_name": "id",
//				"data_type": "NUMERIC",
//				"primary_key": true
//			}, {
//				"excel_name": "excel_2",
//				"field_name": "id_b",
//				"data_type": "NUMERIC",
//				"primary_key": false,
//				"foreign_key": "tabella_b.id"
//			}]
//		}
//	}
	
	private SqlStack getStack() throws Exception {
		String json="{\"tabella_a\":{\"order\":1,\"fields\":[{\"excel_name\":\"excel_1\",\"field_name\":\"id\",\"data_type\":\"NUMERIC\",\"primary_key\":true},{\"excel_name\":\"excel_2\",\"field_name\":\"id_b\",\"data_type\":\"NUMERIC\",\"primary_key\":false,\"foreign_key\":\"tabella_b.id\"}]},\"tabella_b\":{\"order\":0,\"fields\":[{\"excel_name\":\"excel_2\",\"field_name\":\"id\",\"data_type\":\"NUMERIC\",\"primary_key\":true}]}}";
		ObjectMapper mapper = new ObjectMapper();
		SqlStack sqlStack = new SqlStack();
		
		Map<String, Table> tables = mapper.readValue(json, new TypeReference<LinkedHashMap<String, Table>>(){});
		Map<String, Table> tablesSoterd = tables.entrySet().stream().sorted((entry1, entry2) -> entry1.getValue().getOrder() > entry2.getValue().getOrder() ? 1 : 0).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
		sqlStack.setMapConfiguration(tablesSoterd);
		
		for(Entry<String, Table> entryTable : tablesSoterd.entrySet()) {
			String table = entryTable.getKey();
			List<Field> fields = entryTable.getValue().getFields();
			Field primaryKey = Utility.getPrimaryKey(fields, table);
			String primaryKeyName = primaryKey.getFieldName();
			
			String selectSql = SqlBuilder.createSelectSql(table, primaryKeyName);
			String insertSql = SqlBuilder.createInsertSql(table, fields);
			String updateSql = SqlBuilder.createUpdateSql(table, primaryKeyName, fields);
			
			sqlStack.addInQueue(new Sql(selectSql, insertSql, updateSql, primaryKeyName, table));
			
		}
		
//		for(Sql sql : sqlStack.getQueue()) {
//			System.out.println(sql);
//		}
		
		return sqlStack;
	}
	
	/**
	 * @param rows
	 * @throws Exception
	 */
	@Transactional
	public void doImport(List<Map<String, Cell>> rows) throws Exception {
		SqlStack sqlStack = getStack();
		
		// Loop over rows
		for(Map<String, Cell> row : rows) {

			Map<String, FieldValue> rowValues = new HashMap<String, FieldValue>();
			
			// Loop over columns
			for(Entry<String, Cell> entryRow : row.entrySet()) {
				String excelName = entryRow.getKey();
				List<Field> fields = Utility.findFieldsByName(excelName, sqlStack.getMapConfiguration());
				Object value = Utility.getCellValue(entryRow.getValue(), fields.get(0).getDataType());
				List<String> fieldsName = fields.stream().map(f -> f.getTableName()+"."+f.getFieldName()).collect(Collectors.toList());
				rowValues.put(excelName, new FieldValue(fieldsName, value));
			}
			
//			{excel_1=[name=id, value=1.0], excel_2=[name=id, value=1.0]}

			applyQueryOnRow(sqlStack, rowValues);

		}
		
	}
	
	public void applyQueryOnRow(SqlStack sqlStack, Map<String, FieldValue> rowValues) {

		List<Sql> queries = sqlStack.getQueue();
		
		for(Sql sql : queries) {
			FieldValue fieldPrimary = Utility.findValue(rowValues, sql.getTable()+"."+sql.getPrimaryKey());
			Object primaryKeyValue = fieldPrimary.getValue();
			
			Query query = em.createNativeQuery(sql.getSelectByKey());
			query.setParameter(sql.getPrimaryKey(), primaryKeyValue);
			List<?> result = query.getResultList();
			if(!result.isEmpty()) {
				// Update
				System.out.println("@@@@@UPDATE");
			}
			else {
				// Insert
				System.out.println("@@@@@INSERT");
				query = em.createNativeQuery(sql.getInsert());
				
				for(Entry<String, FieldValue> entryValue : rowValues.entrySet()) {
					List<String> fieldsName = entryValue.getValue().getNames();
					String fieldName = fieldsName.stream().filter(f -> f.contains(sql.getTable())).findFirst().orElse(null);
					Object fieldValue = entryValue.getValue().getValue();
					
					if(StringUtils.isNotBlank(fieldName)) {
						fieldName = fieldName.split("\\.")[1];
						query.setParameter(fieldName, fieldValue);
					}
					
					
				}
				
				query.executeUpdate();
				
			}
			
		}
		
	}
	
	public Query performQuery(String queryStr, Map<String, Object> parameters) {
		Query query = em.createNativeQuery(queryStr);
		
		System.out.println(String.format("[%s]", queryStr));
		
		for(Entry<String, Object> entryParameter : parameters.entrySet()) {
			System.out.println(entryParameter.getKey()+": "+entryParameter.getValue());
			query.setParameter(entryParameter.getKey(), entryParameter.getValue());
		}
		
		return query;
	}

}
