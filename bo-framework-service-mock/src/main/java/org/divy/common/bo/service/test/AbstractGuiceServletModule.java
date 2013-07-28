package org.divy.common.bo.service.test;

import org.divy.common.json.SearchQueryMapperProvider;
import org.divy.common.query.service.SearchQueryModule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public abstract class AbstractGuiceServletModule extends JerseyServletModule {

	@Override
	protected void configureServlets() {
		// bind resource classes here

        // hook Jersey into Guice Servlet
        bind(GuiceContainer.class);

        // hook Jackson into Jersey as the POJO <-> JSON mapper
        bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);
        
        bind(JacksonJaxbJsonProvider.class).in(Scopes.SINGLETON);
        bind(SearchQueryMapperProvider.class).in(Scopes.SINGLETON);
        
        configureTestObjects();

        serve("/*").with(GuiceContainer.class);
	}
	
	@Provides @Singleton
	ObjectMapper objectMapper() {
	    final ObjectMapper mapper = new ObjectMapper();
	    mapper.registerModule(new SearchQueryModule());
	    return mapper;
	}

	protected abstract void configureTestObjects();

}
