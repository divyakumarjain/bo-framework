package org.divy.common.bo.rest.builder;

import org.divy.common.bo.rest.RESTEntityURLBuilder;

import java.io.Serializable;

public class UpdateEntityResponseBuilder<T, I extends Serializable> extends ResponseEntityBuilder<T> {
    RESTEntityURLBuilder<T, I> entityURLBuilder;

    UpdateEntityResponseBuilder() {
        super(null);
    }

    public RESTEntityURLBuilder<T, I> getEntityURLBuilder() {
        return entityURLBuilder;
    }

    void setEntityURLBuilder(RESTEntityURLBuilder<T, I> entityURLBuilder) {
        this.entityURLBuilder = entityURLBuilder;
    }
}
