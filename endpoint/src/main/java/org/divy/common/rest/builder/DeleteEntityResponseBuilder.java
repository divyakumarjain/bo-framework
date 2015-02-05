package org.divy.common.rest.builder;

import java.io.Serializable;

import javax.ws.rs.core.Response;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.rest.RESTEntityURLBuilder;

public class DeleteEntityResponseBuilder<T extends IBusinessObject<ID>,ID extends Serializable> extends ResponseEntityBuilder<T> {
    RESTEntityURLBuilder<T,ID> entityURLBuilder;

    public DeleteEntityResponseBuilder(T entity) {
        super(entity);
    }

    @Override
    public Response build() {
        setStatusCode(Response.Status.ACCEPTED);
        return super.build();
    }

    public RESTEntityURLBuilder<T,ID> getEntityURLBuilder() {
        return entityURLBuilder;
    }

    public void setEntityURLBuilder(RESTEntityURLBuilder<T,ID> entityURLBuilder) {
        this.entityURLBuilder = entityURLBuilder;
    }
}
