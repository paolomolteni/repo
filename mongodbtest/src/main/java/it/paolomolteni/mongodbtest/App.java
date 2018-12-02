package it.paolomolteni.mongodbtest;

import java.util.EnumSet;
import java.util.Map;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import it.paolomolteni.mongodbtest.model.Person;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Configuration configuration = getConfiguration();
    	
    	MongoClient mongo = new MongoClient( "localhost" , 27017 );
    	
    	MongoDatabase db = mongo.getDatabase("dbtest");
    	
    	MongoCollection table = db.getCollection("person");
    	
    	// PRIMO INSERIMENTO
//    	BasicDBObject document = new BasicDBObject();
//    	document.put("name", "Mario");
//    	document.put("lastName", "Rossi");
//    	table.insert(document);
    	
    	// SECONDO INSERIMENTO
//    	Map<String,Object> personMap = getObjectMap();
//    	table.insert(new BasicDBObject(personMap));
    	
    	 JsonWriterSettings settings = JsonWriterSettings.builder()
                 .int64Converter((value, writer) -> writer.writeNumber(value.toString()))
                 .build();
    	 
    	 // TERZO INSERIMENTO
    	 Map<String,Object> personMap = getObjectMap();
    	 Document documentIns = new Document(personMap);
    	 String personJson = documentIns.toJson(settings);
    	 table.insertOne(documentIns);
    	 
    	 // Lettura
    	 FindIterable<Document> documents = table.find();
    	 
    	 for(Document document : documents) {
    		 
    		 Person person = configuration.mappingProvider().map(document, Person.class, configuration);
    		 
    		 System.out.println(person.getTest());
    	 }
    	 
    }
    
    private static Map<String,Object> getObjectMap(){
    	Person p1 = new Person("Mario", "Rossi", 6789012345678567L);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	TypeReference<Map<String,Object>> typeRef = new TypeReference<Map<String,Object>>() {};
    	
    	try {
			String jsonInString = mapper.writeValueAsString(p1);
			
			return mapper.readValue(jsonInString, typeRef);
		} 
    	catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    private static Configuration getConfiguration() {
		ObjectMapper mapper = new ObjectMapper();
//		SimpleModule longtypeModule = new SimpleModule();
//		// Fix issue on long value
//		longtypeModule.addDeserializer(Long.class, new LongDeserialize());
//		mapper.registerModule(longtypeModule);
    	
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
		JacksonJsonProvider provider = new JacksonJsonProvider(mapper);
		JacksonMappingProvider mapping = new JacksonMappingProvider(mapper);

		return Configuration.builder()
                 .jsonProvider(provider)
                 .mappingProvider(mapping)
                 .options(EnumSet.noneOf(Option.class)).build();
    }
}
