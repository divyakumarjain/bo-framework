package org.divy.common.rest.response;

import org.divy.common.bo.rest.response.ReadEntityResponseBuilder;

import javax.ws.rs.core.Response;
import java.util.Collection;

public class JerseyReadEntityResponseBuilder<T> extends ReadEntityResponseBuilder<T, Response> {

    public JerseyReadEntityResponseBuilder(T entity) {
        super(entity);
    }

    public JerseyReadEntityResponseBuilder() {
        super();
    }

    @Override
    public Response build() {
        if(entity==null || entity instanceof Collection && ((Collection) entity).isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(entity).build();
        }
    }
}
