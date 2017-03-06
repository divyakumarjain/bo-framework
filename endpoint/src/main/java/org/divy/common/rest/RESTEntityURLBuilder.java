package org.divy.common.rest;


import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.endpoint.AbstractCRUDEndpoint;

import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class RESTEntityURLBuilder<T extends IBusinessObject<I>, I extends Serializable> {

    final Map<Class<T>, Class<? extends AbstractCRUDEndpoint<T, I>>> entityEndPointMap = new HashMap<>();
    @Inject
    LinkBuilderFactoryImpl linkBuilderFactory;

    public RESTEntityURLBuilder(LinkBuilderFactoryImpl linkBuilderFactory) {
        this.linkBuilderFactory = linkBuilderFactory;
    }

    public RESTEntityURLBuilder() {
        //noop
    }

    public Map<Class<T>, Class<? extends AbstractCRUDEndpoint<T, I>>> getEntityEndPointMap() {
        return entityEndPointMap;
    }

    public void addEntityEndPointMap(Class<T> entityClass, Class<? extends AbstractCRUDEndpoint<T, I>> endpointClass) {
        entityEndPointMap.put(entityClass, endpointClass);
    }
    public URI buildEntityUri(T entity, UriInfo uriInfo) {
        //TODO Use uriInfo object in LinkBuilder
        //LinkBuilder linkBuilder = linkBuilderFactory.newBuilder(uriInfo);
        LinkBuilder linkBuilder = linkBuilderFactory.newBuilder();
        final Class<? extends AbstractCRUDEndpoint<T, I>> endPointClass = getEndPointClass(entity);
        return linkBuilder
                .path(endPointClass)
                .path(endPointClass,"get")
                .buildUri(entity.identity());
    }

    public URI buildEntityUri(T entity) {
        return buildEntityUri(entity, null);
    }

    private Class<? extends AbstractCRUDEndpoint<T, I>> getEndPointClass(T entity) {
        return entityEndPointMap.get(entity.getClass());
    }
}
