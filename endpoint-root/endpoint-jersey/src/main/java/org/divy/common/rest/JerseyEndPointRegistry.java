package org.divy.common.rest;

import org.divy.common.bo.repository.Identifiable;
import org.divy.common.bo.rest.EndPointRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JerseyEndPointRegistry implements EndPointRegistry {
    private final Map<String, Class<?>> entityEndPointMap = new HashMap<>();

    @Override
    public void addEntityEndPointMap(String type, Class<?> endpointClass) {
        entityEndPointMap.put(type, endpointClass);
    }
    @Override
    public Class<?> getEndPointClass(Identifiable<UUID> entity) {
        return entityEndPointMap.get(entity._type());
    }
}
