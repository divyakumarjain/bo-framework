package org.divy.common.bo.rest;

import org.divy.common.bo.repository.Identifiable;

public interface EndPointRegistry {
    void addEntityEndPointMap(String type, Class<?> endpointClass);
    <I> Class<?> getEndPointClass(Identifiable<I> entity);
}
