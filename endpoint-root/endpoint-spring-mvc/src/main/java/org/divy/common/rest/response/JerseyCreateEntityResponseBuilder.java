package org.divy.common.rest.response;


import org.divy.common.bo.rest.RESTEntityURLBuilder;
import org.divy.common.bo.rest.response.CreateEntityResponseBuilder;

import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.net.URI;


public class JerseyCreateEntityResponseBuilder<T, I extends Serializable> extends CreateEntityResponseBuilder<T, I, Response> {


    public JerseyCreateEntityResponseBuilder(RESTEntityURLBuilder<T, I> entityURLBuilder) {
        super(entityURLBuilder);
    }

    @Override
    public Response build() {
        return Response.created(createLocation()).build();
    }

    private URI createLocation() {
        return entityURLBuilder.buildEntityUri(getEntity());
    }}
