package org.divy.common.bo.jersey.rest.exception.mapper;

import org.divy.common.exception.NotFoundException;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements
        ExceptionMapper<NotFoundException>
{

    @Override
    public Response toResponse(NotFoundException exception)
    {
        return Response.status(Response.Status.NOT_FOUND)
                .build();
    }
}
