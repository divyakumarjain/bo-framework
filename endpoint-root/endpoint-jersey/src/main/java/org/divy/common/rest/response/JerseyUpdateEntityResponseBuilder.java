package org.divy.common.rest.response;

import org.divy.common.bo.rest.response.UpdateEntityResponseBuilder;

import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Collection;

public class JerseyUpdateEntityResponseBuilder<T>
        extends UpdateEntityResponseBuilder<T, Response> {
    JerseyUpdateEntityResponseBuilder() {
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
