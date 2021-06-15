package org.divy.common.bo.rest.response;


import org.divy.common.bo.rest.RESTEntityURLBuilder;

import java.net.URI;


public abstract class CreateEntityResponseBuilder<T, R> extends AbstractResponseEntityBuilder<T, R> {

    protected RESTEntityURLBuilder<T> entityURLBuilder;

    protected CreateEntityResponseBuilder(RESTEntityURLBuilder<T> entityURLBuilder) {
        super();
        entity(entity);
        this.entityURLBuilder = entityURLBuilder;
    }

    protected URI createLocation() {
        return entityURLBuilder.buildEntityUri(getEntity());
    }
}
