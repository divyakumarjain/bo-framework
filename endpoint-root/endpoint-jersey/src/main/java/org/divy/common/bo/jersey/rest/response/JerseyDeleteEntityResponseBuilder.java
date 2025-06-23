package org.divy.common.bo.jersey.rest.response;

import org.divy.common.bo.rest.response.AbstractResponseEntityBuilder;
import org.divy.common.bo.rest.response.DeleteEntityResponseBuilder;

import jakarta.ws.rs.core.Response;
import java.util.Collection;

public class JerseyDeleteEntityResponseBuilder<T> extends AbstractResponseEntityBuilder<T, Response>
        implements DeleteEntityResponseBuilder<Response> {

    JerseyDeleteEntityResponseBuilder(T entity) {
        super(entity);
    }

    JerseyDeleteEntityResponseBuilder() {
        super();
    }

    @Override
    public Response build() {
        if(entity==null || entity instanceof Collection && ((Collection) entity).isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.OK).entity(entity).build();
        }
    }

}
