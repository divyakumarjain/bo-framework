package org.divy.common.bo.rest.builder;


import org.divy.common.bo.rest.RESTEntityURLBuilder;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.net.URI;


public class CreateEntityResponseBuilder<T, I extends Serializable> extends ResponseEntityBuilder<T> {

    RESTEntityURLBuilder<T, I> entityURLBuilder;

    public CreateEntityResponseBuilder(RESTEntityURLBuilder<T, I> entityURLBuilder) {
        super();
        entity(entity);
        setEntityURLBuilder(entityURLBuilder);
    }

    @Override
    public Response build(UriInfo uriInfo) {
        setStatusCode(Response.Status.CREATED);
        return Response.created(createLocation(uriInfo)).build();
    }

    private URI createLocation(UriInfo uriInfo) {
        return getEntityURLBuilder().buildEntityUri(getEntity(),uriInfo);
    }

    @Override
    public Response build() {
        setStatusCode(Response.Status.CREATED);
        return Response.created(createLocation()).build();
    }

    private URI createLocation() {
        return getEntityURLBuilder().buildEntityUri(getEntity());
    }

    public RESTEntityURLBuilder<T, I> getEntityURLBuilder() {
        return entityURLBuilder;
    }

    public void setEntityURLBuilder(RESTEntityURLBuilder<T, I> entityURLBuilder) {
        this.entityURLBuilder = entityURLBuilder;
    }
}
