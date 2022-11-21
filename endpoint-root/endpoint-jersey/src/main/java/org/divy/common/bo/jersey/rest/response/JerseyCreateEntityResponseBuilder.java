package org.divy.common.bo.jersey.rest.response;


import org.divy.common.bo.rest.RESTEntityURLBuilder;
import org.divy.common.bo.rest.response.CreateEntityResponseBuilder;

import jakarta.ws.rs.core.Response;

public class JerseyCreateEntityResponseBuilder<T> extends CreateEntityResponseBuilder<T, Response> {


    public JerseyCreateEntityResponseBuilder(RESTEntityURLBuilder<T> entityURLBuilder) {
        super(entityURLBuilder);
    }

    @Override
    public Response build() {
        return Response.created(createLocation()).build();
    }
}
