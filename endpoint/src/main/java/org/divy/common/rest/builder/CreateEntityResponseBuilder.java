package org.divy.common.rest.builder;


import org.divy.common.bo.IBusinessObject;
import org.divy.common.rest.RESTEntityURLBuilder;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.net.URI;


public class CreateEntityResponseBuilder<T extends IBusinessObject<I>, I extends Serializable> extends ResponseEntityBuilder<T> {

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

    public RESTEntityURLBuilder<T, I> getEntityURLBuilder() {
        return entityURLBuilder;
    }

    public void setEntityURLBuilder(RESTEntityURLBuilder<T, I> entityURLBuilder) {
        this.entityURLBuilder = entityURLBuilder;
    }
}
