package org.divy.common.bo.rest.builder;

import org.divy.common.bo.rest.RESTEntityURLBuilder;

import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Collection;

public class ReadEntityResponseBuilder<T, I extends Serializable> extends ResponseEntityBuilder<T> {
    RESTEntityURLBuilder<T, I> entityURLBuilder;

    public ReadEntityResponseBuilder(T entity) {
        setEntity(entity);
    }
    @Override
    public Response build() {
        Response.Status statusCode  = getStatusCode();

        if(statusCode==null) {
            if(entity==null || entity instanceof Collection && ((Collection) entity).isEmpty()) {
                setStatusCode(Response.Status.NOT_FOUND);
            } else {
                setStatusCode(Response.Status.OK);
            }
        }
        return super.build();
    }

    public RESTEntityURLBuilder<T, I> getEntityURLBuilder() {
        return entityURLBuilder;
    }

    public void setEntityURLBuilder(RESTEntityURLBuilder<T, I> entityURLBuilder) {
        this.entityURLBuilder = entityURLBuilder;
    }
}
