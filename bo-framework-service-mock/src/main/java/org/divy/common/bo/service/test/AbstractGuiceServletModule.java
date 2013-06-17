package org.divy.common.bo.service.test;

import org.divy.common.bo.service.HibernateBOMapperProvider;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.Scopes;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public abstract class AbstractGuiceServletModule extends ServletModule {

	@Override
	protected void configureServlets() {
		// bind resource classes here

        // hook Jersey into Guice Servlet
        bind(GuiceContainer.class);

        // hook Jackson into Jersey as the POJO <-> JSON mapper
        bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);
        
        bind(JacksonJaxbJsonProvider.class).in(Scopes.SINGLETON);
        bind(HibernateBOMapperProvider.class).in(Scopes.SINGLETON);
        
        configureTestObjects();

        serve("/*").with(GuiceContainer.class);
	}

	protected abstract void configureTestObjects();

}
