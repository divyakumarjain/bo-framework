package org.divy.common.bo.jersey.rest.exception.mapper;

import org.divy.common.exception.NotAuthorizedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotAuthorizedExceptionMapper implements
        ExceptionMapper<NotAuthorizedException>
{

    @Override
    public Response toResponse(NotAuthorizedException exception)
    {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(java.util.Map.of("error", "Unauthorized", "message", exception.getMessage()))
                .type(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                .build();
    }
}
