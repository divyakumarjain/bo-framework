package org.divy.common.bo.jersey.rest.exception.mapper;

import org.divy.common.exception.NotAuthorizedException;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotAuthorizedExceptionMapperTest {

    @Test
    public void testToResponse() {
        NotAuthorizedExceptionMapper mapper = new NotAuthorizedExceptionMapper();
        String exceptionMessage = "Test not authorized exception";
        NotAuthorizedException exception = new NotAuthorizedException(exceptionMessage);

        Response response = mapper.toResponse(exception);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());

        @SuppressWarnings("unchecked")
        Map<String, String> entity = (Map<String, String>) response.getEntity();
        assertEquals("Unauthorized", entity.get("error"));
        assertEquals(exceptionMessage, entity.get("message"));
    }
}
