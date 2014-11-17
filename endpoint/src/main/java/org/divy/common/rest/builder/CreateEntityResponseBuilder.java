package org.divy.common.rest.builder;


import javax.ws.rs.core.Response;

import org.divy.common.bo.IBusinessObject;


public class CreateEntityResponseBuilder<T extends IBusinessObject> extends ResponseEntityBuilder<T> {
    public CreateEntityResponseBuilder(T entity) {
        setEntity(entity);
    }

    public Response build() {
        setStatusCode(Response.Status.CREATED);
        final Response response = Response.created(entityURLBuilder.buildEntityUri(getEntity())).build();
        return response;
    }
}
