/**
 * 
 */
package org.divy.common.bo.service;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module.Feature;

/**
 * @author Divyakumar
 *
 */
@Provider
public class HibernateBOMapperProvider implements ContextResolver<ObjectMapper> {

	private ObjectMapper mapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ContextResolver#getContext(java.lang.Class)
	 */
	@Override
	public ObjectMapper getContext(Class<?> type) {

		if(mapper==null)
			mapper = createContext();

		return mapper;
	}

	protected ObjectMapper createContext() {
		ObjectMapper mapper = new ObjectMapper();

//		mapper.registerModule(new JaxbAnnotationModule());
		Hibernate4Module module = new Hibernate4Module();
		module.configure(Feature.FORCE_LAZY_LOADING, true);
		mapper.registerModule(module);
		return mapper;
	}

}
