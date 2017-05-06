package org.divy.common.bo.rest.response;


public abstract class ReadEntityResponseBuilder<T, R> extends AbstractResponseEntityBuilder<T, R> {
    public ReadEntityResponseBuilder(T entity) {
        this.entity = entity;
    }

    public ReadEntityResponseBuilder() {
    }
}
