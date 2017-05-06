package org.divy.common.rest;


import org.divy.common.bo.Identifiable;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.rest.LinkBuilder;
import org.divy.common.bo.rest.LinkBuilderFactory;
import org.divy.common.bo.rest.RESTEntityURLBuilder;

import javax.inject.Inject;
import javax.ws.rs.core.Link;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JerseyEntityURLBuilderImpl implements RESTEntityURLBuilder<Identifiable<UUID>>, EndPointRegistry {

    private final Map<String, Class<?>> entityEndPointMap = new HashMap<>();


    private LinkBuilderFactory<Link> linkBuilderFactory;

    @Inject
    public JerseyEntityURLBuilderImpl(LinkBuilderFactory<Link> linkBuilderFactory) {
        this.linkBuilderFactory = linkBuilderFactory;
    }

    @Override
    public void addEntityEndPointMap(String type, Class<?> endpointClass) {
        entityEndPointMap.put(type, endpointClass);
    }

    private URI buildEntityUri(Identifiable entity, LinkBuilder<Link> linkBuilder) {
        final Class<?> endPointClass = getEndPointClass(entity);
        return linkBuilder
                .buildURI(endPointClass,"readMethod", entity.identity());
    }

    @Override
    public URI buildEntityUri(Identifiable entity) {
        return buildEntityUri(entity, linkBuilderFactory.newBuilder());
    }

    @Override
    public Class<?> getEndPointClass(Identifiable entity) {
        return entityEndPointMap.get(entity._type());
    }
}
