package org.divy.common.rest.builder;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.rest.RESTEntityURLBuilder;

import javax.ws.rs.core.Response;
import java.io.Serializable;

public class DeleteEntityResponseBuilder<T extends IBusinessObject<I>, I extends Serializable> extends ResponseEntityBuilder<T> {
    RESTEntityURLBuilder<T, I> entityURLBuilder;

    public DeleteEntityResponseBuilder(T entity) {
        super(entity);
    }

    @Override
    public Response build() {
        setStatusCode(Response.Status.ACCEPTED);
        return super.build();
    }

    public RESTEntityURLBuilder<T, I> getEntityURLBuilder() {
        return entityURLBuilder;
    }

    public void setEntityURLBuilder(RESTEntityURLBuilder<T, I> entityURLBuilder) {
        this.entityURLBuilder = entityURLBuilder;
    }
}
