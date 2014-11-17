package org.divy.common.rest.builder;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.divy.common.rest.RESTEntityURLBuilder;

public class ResponseEntityBuilder<T> {
    T entity;
    private Status statusCode;

    @Inject
    RESTEntityURLBuilder entityURLBuilder;

    public Response build(){
        setStatusCode(Status.OK);

        if (getStatusCode() == null) {
            setStatusCode(getEntity()==null ? Status.NO_CONTENT : Status.OK);
        }

        final Response response = javax.ws.rs.core.Response.status(getStatusCode()).entity(getEntity()).build();

        return response;
    }

    /* Builder methods*/

    public ResponseEntityBuilder<T> statusCode(Status statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public ResponseEntityBuilder<T> entity(T entity) {
        this.entity = entity;
        return this;
    }

    /* Getter Setters methods*/


    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Status getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Status statusCode) {
        this.statusCode = statusCode;
    }
}
