package org.divy.common.bo.rest;

import org.divy.common.bo.Identifiable;

import java.util.UUID;

public interface EndPointRegistry {
    void addEntityEndPointMap(String type, Class<?> endpointClass);
    Class<?> getEndPointClass(Identifiable<UUID> entity);
}
