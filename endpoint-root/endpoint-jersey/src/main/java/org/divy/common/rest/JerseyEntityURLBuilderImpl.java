package org.divy.common.rest;


import org.divy.common.bo.repository.Identifiable;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.rest.LinkBuilder;
import org.divy.common.bo.rest.LinkBuilderFactory;
import org.divy.common.bo.rest.RESTEntityURLBuilder;

import javax.inject.Inject;
import javax.ws.rs.core.Link;
import java.net.URI;
import java.util.UUID;

public class JerseyEntityURLBuilderImpl implements RESTEntityURLBuilder<Identifiable<UUID>>{

    private LinkBuilderFactory<Link> linkBuilderFactory;

    private EndPointRegistry endPointRegistry;

    @Inject
    public JerseyEntityURLBuilderImpl(LinkBuilderFactory<Link> linkBuilderFactory
    ,EndPointRegistry endPointRegistry) {
        this.linkBuilderFactory = linkBuilderFactory;
        this.endPointRegistry = endPointRegistry;
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

    private Class<?> getEndPointClass( Identifiable entity ) {
        return endPointRegistry.getEndPointClass(entity);
    }
}
