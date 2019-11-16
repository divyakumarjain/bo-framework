package org.divy.common.bo.jersey.rest.response;

import org.divy.common.bo.rest.response.AbstractResponseEntityBuilder;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class JerseyResponseEntityBuilder<T> extends AbstractResponseEntityBuilder<T, Response> {
    private Status statusCode;

    JerseyResponseEntityBuilder(T entity) {
        super(entity);
    }

    JerseyResponseEntityBuilder() {
        super();
    }

    /* Builder methods*/

    public JerseyResponseEntityBuilder<T> statusCode(Status statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    @Override
    public JerseyResponseEntityBuilder<T> entity(T entity) {
        this.entity = entity;
        return this;
    }

    /* Getter Setters methods*/


    @Override
    public T getEntity() {
        return entity;
    }

    @Override
    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Status getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Status statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public Response build() {
        if (getStatusCode() == null) {
            setStatusCode(getEntity()==null ? Status.NO_CONTENT : Status.OK);
        }
        return Response.status(getStatusCode()).entity(getEntity()).build();
    }

}
