package org.divy.common.rest;


import java.io.Serializable;
import java.net.URI;
import javax.inject.Inject;

import org.divy.common.bo.IBusinessObject;

public class RESTEntityURLBuilder<T extends IBusinessObject<K>,K extends Serializable> {

    @Inject
    LinkBuilderFactory linkBuilderFactory;

    public URI buildEntityUri(T entity, Class<?> clazz) {
        LinkBuilder linkBuilder = linkBuilderFactory.newBuilder();
        return linkBuilder.path(clazz,"read").buildUri(entity.identity());
    }

    public URI buildEntityUri(T entity) {
        return buildEntityUri(entity, getEndPointClass(entity));
    }

    private Class<?> getEndPointClass(T entity) {
        return null;
    }
}
