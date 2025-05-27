package org.divy.common.bo.jersey.rest.exception.mapper;

import org.divy.common.exception.BadRequestException;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BadRequestExceptionMapperTest {

    @Test
    public void testToResponse() {
        BadRequestExceptionMapper mapper = new BadRequestExceptionMapper();
        String exceptionMessage = "Test bad request exception";
        BadRequestException exception = new BadRequestException(exceptionMessage);

        Response response = mapper.toResponse(exception);

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

        @SuppressWarnings("unchecked")
        Map<String, String> entity = (Map<String, String>) response.getEntity();
        assertEquals("Bad Request", entity.get("error"));
        assertEquals(exceptionMessage, entity.get("message"));
    }
}
