package org.divy.common.bo.spring.mvc.graphql.response;

import org.divy.common.bo.rest.response.AbstractResponseEntityBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class SpringMVCResponseEntityBuilder<T> extends AbstractResponseEntityBuilder<T, ResponseEntity> {
    private HttpStatus statusCode;

    SpringMVCResponseEntityBuilder(T entity) {
        super(entity);
    }

    SpringMVCResponseEntityBuilder() {
        super();
    }

    /* Builder methods*/

    public SpringMVCResponseEntityBuilder<T> statusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    @Override
    public SpringMVCResponseEntityBuilder<T> entity(T entity) {
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

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public ResponseEntity build() {
        if (getStatusCode() == null) {
            setStatusCode(getEntity()==null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
        }
        return ResponseEntity.status(getStatusCode()).body(getEntity());
    }

}
