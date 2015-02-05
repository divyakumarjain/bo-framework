package org.divy.common.rest.builder;

import java.io.Serializable;
import java.util.Collection;

import javax.ws.rs.core.Response;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.rest.RESTEntityURLBuilder;

public class ReadEntityResponseBuilder<T extends IBusinessObject<ID>,ID extends Serializable> extends ResponseEntityBuilder<T> {
    RESTEntityURLBuilder<T,ID> entityURLBuilder;

    public ReadEntityResponseBuilder(T entity) {
        setEntity(entity);
    }
    @Override
    @SuppressWarnings("unchecked")
    public Response build() {
        Response.Status statusCode  = getStatusCode();

        if(statusCode==null) {
            if(entity==null || entity instanceof Collection && ((Collection<T>) entity).size()<1) {
                setStatusCode(Response.Status.NOT_FOUND);
            } else {
                setStatusCode(Response.Status.OK);
            }
        }
        return super.build();
    }

    public RESTEntityURLBuilder<T,ID> getEntityURLBuilder() {
        return entityURLBuilder;
    }

    public void setEntityURLBuilder(RESTEntityURLBuilder<T,ID> entityURLBuilder) {
        this.entityURLBuilder = entityURLBuilder;
    }
}
