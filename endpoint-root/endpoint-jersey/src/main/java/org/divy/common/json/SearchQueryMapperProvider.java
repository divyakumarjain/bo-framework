package org.divy.common.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

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
        return new ObjectMapper();
    }

}
