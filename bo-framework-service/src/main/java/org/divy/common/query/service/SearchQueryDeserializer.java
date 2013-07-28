package org.divy.common.query.service;

import java.io.IOException;

import org.divy.common.bo.query.IOperator;
import org.divy.common.bo.query.defaults.EqualTo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

final class SearchQueryDeserializer extends
		JsonDeserializer<IOperator> {
	@Override
	public IOperator deserialize(JsonParser jp,
			DeserializationContext ctxt) throws IOException,
			JsonProcessingException {
		
		IOperator returnOperator = null;
		
		ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);
        JsonNode operatorValue = node.get("value");
        
        //TODO support all operators
        if(operatorValue!=null)
        	returnOperator = new EqualTo<String>(operatorValue.asText());
        
        return returnOperator;
	}
}