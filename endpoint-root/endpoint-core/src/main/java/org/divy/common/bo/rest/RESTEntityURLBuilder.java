package org.divy.common.bo.rest;

import java.net.URI;

@FunctionalInterface
public interface RESTEntityURLBuilder<T> {
    URI buildEntityUri(T entity);
}
