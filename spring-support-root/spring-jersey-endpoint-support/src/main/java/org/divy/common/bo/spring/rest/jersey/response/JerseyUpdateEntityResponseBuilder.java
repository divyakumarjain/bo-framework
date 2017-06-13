package org.divy.common.bo.spring.rest.jersey.response;

import org.divy.common.bo.rest.response.UpdateEntityResponseBuilder;

import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Collection;

public class JerseyUpdateEntityResponseBuilder<T, I extends Serializable> extends UpdateEntityResponseBuilder {
    JerseyUpdateEntityResponseBuilder() {
        super();
    }

    @Override
    public Response build() {
        if(entity==null || entity instanceof Collection && ((Collection) entity).isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.OK).build();
        }
    }
}
