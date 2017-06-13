package org.divy.common.rest.response;

import org.divy.common.bo.rest.RESTEntityURLBuilder;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Collection;


public class JerseyResponseEntityBuilderFactory<T, I extends Serializable> implements ResponseEntityBuilderFactory<T, I, Response> {

    RESTEntityURLBuilder<T, I> entityURLBuilder;

    @Inject
    public JerseyResponseEntityBuilderFactory(RESTEntityURLBuilder<T, I> entityURLBuilder) {
        this.entityURLBuilder = entityURLBuilder;
    }

    /* Builder static methods*/

    public JerseyCreateEntityResponseBuilder<T, I> create(T entity) {
        final JerseyCreateEntityResponseBuilder<T, I> createEntityResponseBuilder = new JerseyCreateEntityResponseBuilder<>(entityURLBuilder);
        createEntityResponseBuilder.entity(entity);
        return createEntityResponseBuilder;
    }

    public JerseyUpdateEntityResponseBuilder<T, I> update() {
        JerseyUpdateEntityResponseBuilder<T, I> updateEntityResponseBuilder = new JerseyUpdateEntityResponseBuilder<>();
        return updateEntityResponseBuilder;
    }

    public JerseyDeleteEntityResponseBuilder<T, I> delete(T entity) {
        JerseyDeleteEntityResponseBuilder<T, I> deleteEntityResponseBuilder = new JerseyDeleteEntityResponseBuilder<>(entity);
        return deleteEntityResponseBuilder;
    }

    public JerseyDeleteEntityResponseBuilder<T, I> delete() {
        JerseyDeleteEntityResponseBuilder<T, I> deleteEntityResponseBuilder = new JerseyDeleteEntityResponseBuilder<>();
        return deleteEntityResponseBuilder;
    }

    public JerseyReadEntityResponseBuilder<T, I> read(T entity) {
        JerseyReadEntityResponseBuilder<T, I> readEntityResponseBuilder = new JerseyReadEntityResponseBuilder<>(entity);
        return readEntityResponseBuilder;
    }

    public JerseyReadEntityResponseBuilder<T, I> read() {
        JerseyReadEntityResponseBuilder<T, I> readEntityResponseBuilder = new JerseyReadEntityResponseBuilder<>();
        return readEntityResponseBuilder;
    }

    public JerseyListEntityResponseBuilder<T> list(Collection<T> list) {
        return new JerseyListEntityResponseBuilder<T>(list);
    }
}
