package org.divy.common.bo.spring.mvc.rest;

import org.divy.common.bo.repository.Identifiable;
import org.divy.common.bo.rest.EndPointRegistry;

import java.util.HashMap;
import java.util.Map;

public class SpringMVCEndPointRegistry implements EndPointRegistry {
    private final Map<String, Class<?>> entityEndPointMap = new HashMap<>();

    @Override
    public void addEntityEndPointMap(String type, Class<?> endpointClass) {
        entityEndPointMap.put(type, endpointClass);
    }
    @Override
    public <I> Class<?> getEndPointClass(Identifiable<I> entity) {
        return entityEndPointMap.get(entity._type());
    }
}
