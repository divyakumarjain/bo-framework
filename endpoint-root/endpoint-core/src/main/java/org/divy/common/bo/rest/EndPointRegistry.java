package org.divy.common.bo.rest;

import org.divy.common.bo.IBusinessObject;

public interface EndPointRegistry {
    void addEntityEndPointMap(Class<? extends IBusinessObject> entityClass, Class<?> endpointClass);
}
