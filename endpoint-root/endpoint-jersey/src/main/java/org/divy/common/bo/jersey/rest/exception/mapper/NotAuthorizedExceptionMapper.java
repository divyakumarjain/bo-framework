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
        return Response.status(Response.Status.NOT_FOUND)
                .build();
    }
}
