package org.divy.common.bo.rest.response;

import java.util.Collection;


public interface ResponseEntityBuilderFactory<T, R> {

    CreateEntityResponseBuilder<T, R> create(T entity);

    UpdateEntityResponseBuilder<T, R> update();

    DeleteEntityResponseBuilder<R> delete(T entity);

    DeleteEntityResponseBuilder<R> delete();

    ReadEntityResponseBuilder<T, R> read(T entity);

    ReadEntityResponseBuilder<T, R> read();

    ListEntityResponseBuilder<Collection<T>, R> list(Collection<T> list);
}
