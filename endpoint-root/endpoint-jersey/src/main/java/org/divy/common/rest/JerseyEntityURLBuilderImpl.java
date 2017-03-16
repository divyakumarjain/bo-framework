package org.divy.common.rest;


import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.endpoint.AbstractBOJerseyEndpoint;
import org.divy.common.bo.rest.LinkBuilder;
import org.divy.common.bo.rest.LinkBuilderFactoryImpl;
import org.divy.common.bo.rest.RESTEntityURLBuilder;

import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class JerseyEntityURLBuilderImpl<T extends IBusinessObject<I>, I extends Serializable> implements RESTEntityURLBuilder<T, I> {

    final Map<Class<T>, Class<? extends AbstractBOJerseyEndpoint<T, I>>> entityEndPointMap = new HashMap<>();
    @Inject
    LinkBuilderFactoryImpl linkBuilderFactory;

    public JerseyEntityURLBuilderImpl(LinkBuilderFactoryImpl linkBuilderFactory) {
        this.linkBuilderFactory = linkBuilderFactory;
    }

    public JerseyEntityURLBuilderImpl() {
        //noop
    }

    public Map<Class<T>, Class<? extends AbstractBOJerseyEndpoint<T, I>>> getEntityEndPointMap() {
        return entityEndPointMap;
    }

    public void addEntityEndPointMap(Class<T> entityClass, Class<? extends AbstractBOJerseyEndpoint<T, I>> endpointClass) {
        entityEndPointMap.put(entityClass, endpointClass);
    }
    @Override
    public URI buildEntityUri(T entity, UriInfo uriInfo) {
        //TODO Use uriInfo object in LinkBuilder
        //LinkBuilder linkBuilder = linkBuilderFactory.newBuilder(uriInfo);
        LinkBuilder linkBuilder = linkBuilderFactory.newBuilder();
        final Class<? extends AbstractBOJerseyEndpoint<T, I>> endPointClass = getEndPointClass(entity);
        return linkBuilder
                .path(endPointClass)
                .path(endPointClass,"get")
                .buildUri(entity.identity());
    }

    @Override
    public URI buildEntityUri(T entity) {
        return buildEntityUri(entity, null);
    }

    private Class<? extends AbstractBOJerseyEndpoint<T, I>> getEndPointClass(T entity) {
        return entityEndPointMap.get(entity.getClass());
    }
}
