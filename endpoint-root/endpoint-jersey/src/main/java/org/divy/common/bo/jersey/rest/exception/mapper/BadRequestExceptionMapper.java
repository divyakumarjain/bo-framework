package org.divy.common.bo.jersey.rest.exception.mapper;

import org.divy.common.exception.BadRequestException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionMapper implements
        ExceptionMapper<BadRequestException>
{

    @Override
    public Response toResponse(BadRequestException exception)
    {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(java.util.Map.of("error", "Bad Request", "message", exception.getMessage()))
                .type(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                .build();
    }
}
