package org.divy.common.rest;


import java.io.Serializable;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.endpoint.AbstractCRUDEndpoint;

public class RESTEntityURLBuilder<T extends IBusinessObject<ID>, ID extends Serializable> {

    @Inject
    LinkBuilderFactory linkBuilderFactory;


    final Map<Class<T>,Class<? extends AbstractCRUDEndpoint<T,ID>>> entityEndPointMap = new HashMap<>();

    public RESTEntityURLBuilder(LinkBuilderFactory linkBuilderFactory) {
        this.linkBuilderFactory = linkBuilderFactory;
    }

    public RESTEntityURLBuilder() {
    }

    public Map<Class<T>,Class<? extends AbstractCRUDEndpoint<T,ID>>> getEntityEndPointMap() {
        return entityEndPointMap;
    }

    public void addEntityEndPointMap(Class<T> entityClass, Class<? extends AbstractCRUDEndpoint<T,ID>> endpointClass) {
        entityEndPointMap.put(entityClass, endpointClass);
    }
    public URI buildEntityUri(T entity, UriInfo uriInfo) {
        //TODO Use uriInfo object in LinkBuilder
        //LinkBuilder linkBuilder = linkBuilderFactory.newBuilder(uriInfo);
        LinkBuilder linkBuilder = linkBuilderFactory.newBuilder();
        final Class<? extends AbstractCRUDEndpoint<T,ID>> endPointClass = getEndPointClass(entity);
        return linkBuilder
                .path(endPointClass)
                .path(endPointClass,"get")
                .buildUri(entity.identity());
    }

    public URI buildEntityUri(T entity) {
        return buildEntityUri(entity, null);
    }

    private Class<? extends AbstractCRUDEndpoint<T,ID>> getEndPointClass(T entity) {
        return entityEndPointMap.get(entity.getClass());
    }
}
