package org.divy.common.bo.rest.builder;

import org.divy.common.bo.rest.RESTEntityURLBuilder;

import java.io.Serializable;

public class DeleteEntityResponseBuilder<T, I extends Serializable> extends ResponseEntityBuilder<T> {
    RESTEntityURLBuilder<T, I> entityURLBuilder;

    DeleteEntityResponseBuilder(T entity) {
        super(entity);
    }

    DeleteEntityResponseBuilder() {

    }

    public RESTEntityURLBuilder<T, I> getEntityURLBuilder() {
        return entityURLBuilder;
    }

    void setEntityURLBuilder(RESTEntityURLBuilder<T, I> entityURLBuilder) {
        this.entityURLBuilder = entityURLBuilder;
    }
}
