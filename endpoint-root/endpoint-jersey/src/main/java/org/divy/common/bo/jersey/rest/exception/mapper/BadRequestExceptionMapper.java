package org.divy.common.bo.jersey.rest.exception.mapper;

import org.divy.common.exception.BadRequestException;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionMapper implements
        ExceptionMapper<BadRequestException>
{

    @Override
    public Response toResponse(BadRequestException exception)
    {
        return Response.status(Response.Status.NOT_FOUND)
                .build();
    }
}
