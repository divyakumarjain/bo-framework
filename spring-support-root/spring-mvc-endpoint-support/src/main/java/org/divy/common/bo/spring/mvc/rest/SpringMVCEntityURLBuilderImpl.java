package org.divy.common.bo.spring.mvc.rest;


import org.divy.common.bo.repository.Identifiable;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.rest.LinkBuilder;
import org.divy.common.bo.rest.LinkBuilderFactory;
import org.divy.common.bo.rest.RESTEntityURLBuilder;
import org.springframework.hateoas.Link;

import java.net.URI;

public class SpringMVCEntityURLBuilderImpl<T extends Identifiable<I>, I> implements RESTEntityURLBuilder<T> {

    private LinkBuilderFactory<Link> linkBuilderFactory;
    private EndPointRegistry endPointRegistry;

    public SpringMVCEntityURLBuilderImpl(LinkBuilderFactory<Link> linkBuilderFactory, EndPointRegistry endPointRegistry) {
        this.linkBuilderFactory = linkBuilderFactory;
        this.endPointRegistry = endPointRegistry;
    }

    private URI buildEntityUri(T entity, LinkBuilder<Link> linkBuilder) {
        final Class<?> endPointClass = endPointRegistry.getEndPointClass(entity);
        return linkBuilder
                .buildURI(endPointClass,"readMethod", entity.identity());
    }

    @Override
    public URI buildEntityUri(T entity) {
        return buildEntityUri(entity, linkBuilderFactory.newBuilder());
    }
}
