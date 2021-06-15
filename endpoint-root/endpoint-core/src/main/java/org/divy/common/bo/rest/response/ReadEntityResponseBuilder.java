package org.divy.common.bo.rest.response;


public abstract class ReadEntityResponseBuilder<T, R> extends AbstractResponseEntityBuilder<T, R> {
    protected ReadEntityResponseBuilder(T entity) {
        this.entity = entity;
    }

    protected ReadEntityResponseBuilder() {
    }
}
