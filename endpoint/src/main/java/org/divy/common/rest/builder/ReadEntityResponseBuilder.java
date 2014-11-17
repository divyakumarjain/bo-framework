package org.divy.common.rest.builder;

import java.util.Collection;
import javax.ws.rs.core.Response;

import org.divy.common.bo.IBusinessObject;

public class ReadEntityResponseBuilder<T extends IBusinessObject> extends ResponseEntityBuilder<T> {
    public ReadEntityResponseBuilder(T entity) {
        setEntity(entity);
    }
    @Override
    public Response build() {
        Response.Status statusCode  = getStatusCode();

        if(statusCode==null) {
            if(entity==null || entity instanceof Collection && ((Collection) entity).size()<1) {
                setStatusCode(Response.Status.NOT_FOUND);
            } else {
                setStatusCode(Response.Status.OK);
            }
        }
        return super.build();
    }
}
