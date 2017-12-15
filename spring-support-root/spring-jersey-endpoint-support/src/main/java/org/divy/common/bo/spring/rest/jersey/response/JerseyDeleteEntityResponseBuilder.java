package org.divy.common.bo.spring.rest.jersey.response;

import org.divy.common.bo.rest.response.AbstractResponseEntityBuilder;
import org.divy.common.bo.rest.response.DeleteEntityResponseBuilder;

import javax.ws.rs.core.Response;
import java.io.Serializable;

public class JerseyDeleteEntityResponseBuilder<T, I extends Serializable> extends AbstractResponseEntityBuilder<T, Response>
        implements DeleteEntityResponseBuilder<Response> {

    JerseyDeleteEntityResponseBuilder(T entity) {
        super(entity);
    }

    JerseyDeleteEntityResponseBuilder() {
        super();
    }

    @Override
    public Response build() {
        return Response.ok().build();
    }

}
