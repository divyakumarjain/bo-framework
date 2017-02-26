package org.divy.common.rest.builder;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

public class ResponseEntityBuilder<T> {
    T entity;
    private Status statusCode;

    public ResponseEntityBuilder(T entity) {
        setEntity(entity);
    }

    protected ResponseEntityBuilder() {
    }

    public Response build(){
        return build(null);
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

    public Response build(UriInfo uriInfo) {

        if (getStatusCode() == null) {
            setStatusCode(getEntity()==null ? Status.NO_CONTENT : Status.OK);
        }

        return Response.status(getStatusCode()).entity(getEntity()).build();
    }

}
