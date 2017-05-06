package org.divy.common.bo.rest.response;

import org.divy.common.bo.rest.RESTEntityURLBuilder;

public abstract class UpdateEntityResponseBuilder<T, R> extends AbstractResponseEntityBuilder<T, R> {
    private RESTEntityURLBuilder<T> entityURLBuilder;

    protected UpdateEntityResponseBuilder() {
        super(null);
    }

    protected UpdateEntityResponseBuilder(T entity) {
        super(entity);
    }

    public RESTEntityURLBuilder<T> getEntityURLBuilder() {
        return entityURLBuilder;
    }

    void setEntityURLBuilder(RESTEntityURLBuilder<T> entityURLBuilder) {
        this.entityURLBuilder = entityURLBuilder;
    }
}
