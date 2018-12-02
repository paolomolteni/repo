package it.paolomolteni.mongodbtest;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LongDeserialize extends StdDeserializer<Long> {

	/**
	 * 
	 */
	public LongDeserialize() {
		super(Long.class);
	}

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
	 */
	@Override
	public Long deserialize(JsonParser jsonparser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		
		String value = jsonparser.getText();
		
		try {
			return Long.parseLong(value);
		}
		catch (Exception e) {
			return null;
		}
		
	}

}
