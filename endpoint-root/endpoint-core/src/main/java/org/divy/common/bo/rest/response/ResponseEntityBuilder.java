package org.divy.common.bo.rest.response;

@FunctionalInterface
public interface ResponseEntityBuilder<R> {
    R build();
}
