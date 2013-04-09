/**
 * 
 */
package org.divy.common.bo.service.json;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
//import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * @author divyakumar.a.jain@hp.com
 *
 */
@Provider
public class HibernateBOMapperProvider implements ContextResolver<ObjectMapper> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ContextResolver#getContext(java.lang.Class)
	 */
	@Override
	public ObjectMapper getContext(Class<?> type) {

		ObjectMapper mapper = new ObjectMapper();

		mapper.registerModule(new JaxbAnnotationModule());
		mapper.registerModule(new Hibernate4Module());

		return mapper;
	}

}
