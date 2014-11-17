package org.divy.common.rest.builder;

import javax.ws.rs.core.Response;

import org.divy.common.bo.IBusinessObject;

public class DeleteEntityResponseBuilder<T extends IBusinessObject> extends ResponseEntityBuilder<T> {
    @Override
    public Response build() {
        setStatusCode(Response.Status.ACCEPTED);
        return super.build();
    }
}
