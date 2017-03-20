package org.divy.common.rest;


import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.rest.LinkBuilder;
import org.divy.common.bo.rest.LinkBuilderFactoryImpl;
import org.divy.common.bo.rest.RESTEntityURLBuilder;

import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JerseyEntityURLBuilderImpl implements RESTEntityURLBuilder<IBusinessObject<UUID>, UUID>, EndPointRegistry {

    private final Map<Class<? extends IBusinessObject>, Class<?>> entityEndPointMap = new HashMap<>();


    private LinkBuilderFactoryImpl linkBuilderFactory;

    @Inject
    public JerseyEntityURLBuilderImpl(LinkBuilderFactoryImpl linkBuilderFactory) {
        this.linkBuilderFactory = linkBuilderFactory;
    }

    @Override
    public void addEntityEndPointMap(Class<? extends IBusinessObject> entityClass, Class<?> endpointClass) {
        entityEndPointMap.put(entityClass, endpointClass);
    }

    @Override
    public URI buildEntityUri(IBusinessObject entity, UriInfo uriInfo) {
        LinkBuilder linkBuilder = linkBuilderFactory.newBuilder(uriInfo);
        return buildEntityUri(entity, linkBuilder);
    }

    private URI buildEntityUri(IBusinessObject entity, LinkBuilder linkBuilder) {
        final Class<?> endPointClass = getEndPointClass(entity);
        return linkBuilder
                .path(endPointClass)
                .path(endPointClass,"readEndPoint")
                .buildUri(entity.identity());
    }

    @Override
    public URI buildEntityUri(IBusinessObject entity) {
        return buildEntityUri(entity, linkBuilderFactory.newBuilder());
    }

    private Class<?> getEndPointClass(IBusinessObject entity) {
        return entityEndPointMap.get(entity.getClass());
    }
}
