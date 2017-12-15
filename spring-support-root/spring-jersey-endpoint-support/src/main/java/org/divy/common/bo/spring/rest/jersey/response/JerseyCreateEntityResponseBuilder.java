package org.divy.common.bo.spring.rest.jersey.response;


import org.divy.common.bo.rest.RESTEntityURLBuilder;
import org.divy.common.bo.rest.response.CreateEntityResponseBuilder;

import javax.ws.rs.core.Response;
import java.net.URI;


public class JerseyCreateEntityResponseBuilder<T> extends CreateEntityResponseBuilder<T, Response> {


    public JerseyCreateEntityResponseBuilder(RESTEntityURLBuilder<T> entityURLBuilder) {
        super(entityURLBuilder);
    }

    @Override
    public Response build() {
        return Response.created(createLocation()).build();
    }

    @Override
    protected URI createLocation() {
        return entityURLBuilder.buildEntityUri(getEntity());
    }
}
