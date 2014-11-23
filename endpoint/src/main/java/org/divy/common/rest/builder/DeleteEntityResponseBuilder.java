package org.divy.common.rest.builder;

import javax.ws.rs.core.Response;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.rest.RESTEntityURLBuilder;

public class DeleteEntityResponseBuilder<T extends IBusinessObject> extends ResponseEntityBuilder<T> {
    RESTEntityURLBuilder<T> entityURLBuilder;

    public DeleteEntityResponseBuilder(T entity) {
        super(entity);
    }

    @Override
    public Response build() {
        setStatusCode(Response.Status.ACCEPTED);
        return super.build();
    }

    public RESTEntityURLBuilder<T> getEntityURLBuilder() {
        return entityURLBuilder;
    }

    public void setEntityURLBuilder(RESTEntityURLBuilder<T> entityURLBuilder) {
        this.entityURLBuilder = entityURLBuilder;
    }
}
