package org.divy.common.rest.builder;


import java.io.Serializable;
import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.rest.RESTEntityURLBuilder;


public class CreateEntityResponseBuilder<T extends IBusinessObject<ID>,ID extends Serializable> extends ResponseEntityBuilder<T> {

    RESTEntityURLBuilder<T,ID> entityURLBuilder;

    public CreateEntityResponseBuilder(RESTEntityURLBuilder<T,ID> entityURLBuilder) {
        super();
        entity(entity);
        setEntityURLBuilder(entityURLBuilder);
    }

    public Response build(UriInfo uriInfo) {
        setStatusCode(Response.Status.CREATED);
        final Response response = Response.created(createLocation(uriInfo)).build();
        return response;
    }

    private URI createLocation(UriInfo uriInfo) {
        return getEntityURLBuilder().buildEntityUri(getEntity(),uriInfo);
    }

    public RESTEntityURLBuilder<T,ID> getEntityURLBuilder() {
        return entityURLBuilder;
    }

    public void setEntityURLBuilder(RESTEntityURLBuilder<T,ID> entityURLBuilder) {
        this.entityURLBuilder = entityURLBuilder;
    }
}
