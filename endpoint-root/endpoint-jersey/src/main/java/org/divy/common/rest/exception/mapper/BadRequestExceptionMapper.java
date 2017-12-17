package org.divy.common.rest.exception.mapper;

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
        return Response.status(Response.Status.NOT_FOUND)
                .build();
    }
}
