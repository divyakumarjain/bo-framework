package org.divy.common.json;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.divy.common.query.service.SearchQueryModule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module.Feature;

@Provider
public class SearchQueryMapperProvider implements ContextResolver<ObjectMapper>{

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

//        mapper.registerModule(new JaxbAnnotationModule());
        Hibernate4Module module = new Hibernate4Module();
        module.configure(Feature.FORCE_LAZY_LOADING, true);
        mapper.registerModule(module);
        mapper.registerModule(new SearchQueryModule());
        return mapper;
    }

}
