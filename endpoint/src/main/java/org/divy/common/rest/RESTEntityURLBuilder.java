package org.divy.common.rest;


import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.UriInfo;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.endpoint.AbstractCRUDEndpoint;

public class RESTEntityURLBuilder<T extends IBusinessObject> {

    @Inject
    LinkBuilderFactory linkBuilderFactory;


    final static Map<Class<? extends IBusinessObject>,Class<? extends AbstractCRUDEndpoint>> entityEndPointMap = new HashMap<>();

    public RESTEntityURLBuilder(LinkBuilderFactory linkBuilderFactory) {
        this.linkBuilderFactory = linkBuilderFactory;
    }

    public RESTEntityURLBuilder() {
        this.linkBuilderFactory = linkBuilderFactory;
    }

    public Map<Class<? extends IBusinessObject>, Class<? extends AbstractCRUDEndpoint>> getEntityEndPointMap() {
        return entityEndPointMap;
    }

    public static void addEntityEndPointMap(Class<? extends IBusinessObject> entityClass, Class<? extends AbstractCRUDEndpoint> endpointClass) {
        entityEndPointMap.put(entityClass, endpointClass);
    }
    public URI buildEntityUri(T entity, UriInfo uriInfo) {
        //TODO Use uriInfo object in LinkBuilder
        //LinkBuilder linkBuilder = linkBuilderFactory.newBuilder(uriInfo);
        LinkBuilder linkBuilder = linkBuilderFactory.newBuilder();
        final Class<? extends AbstractCRUDEndpoint> endPointClass = getEndPointClass(entity);
        return linkBuilder
                .path(endPointClass)
                .path(endPointClass,"get")
                .buildUri(entity.identity());
    }

    public URI buildEntityUri(T entity) {
        return buildEntityUri(entity, null);
    }

    private Class<? extends AbstractCRUDEndpoint> getEndPointClass(T entity) {
        return entityEndPointMap.get(entity.getClass());
    }
}
