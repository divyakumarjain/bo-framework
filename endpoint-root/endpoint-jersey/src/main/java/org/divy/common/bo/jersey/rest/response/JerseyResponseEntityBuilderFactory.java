package org.divy.common.bo.jersey.rest.response;

import org.divy.common.bo.rest.RESTEntityURLBuilder;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Collection;


public class JerseyResponseEntityBuilderFactory<T> implements ResponseEntityBuilderFactory<T, Response> {

    private RESTEntityURLBuilder<T> entityURLBuilder;

    @Inject
    public JerseyResponseEntityBuilderFactory(RESTEntityURLBuilder<T> entityURLBuilder) {
        this.entityURLBuilder = entityURLBuilder;
    }

    /* Builder static methods*/

    @Override
    public JerseyCreateEntityResponseBuilder<T> create(T entity) {
        final JerseyCreateEntityResponseBuilder<T> createEntityResponseBuilder = new JerseyCreateEntityResponseBuilder<>(entityURLBuilder);
        createEntityResponseBuilder.entity(entity);
        return createEntityResponseBuilder;
    }

    @Override
    public JerseyUpdateEntityResponseBuilder<T> update() {
        return new JerseyUpdateEntityResponseBuilder<>();
    }

    @Override
    public JerseyDeleteEntityResponseBuilder<T> delete(T entity) {
        return new JerseyDeleteEntityResponseBuilder<>(entity);
    }

    @Override
    public JerseyDeleteEntityResponseBuilder<T> delete() {
        return new JerseyDeleteEntityResponseBuilder<>();
    }

    @Override
    public JerseyReadEntityResponseBuilder<T> read(T entity) {
        return new JerseyReadEntityResponseBuilder<>(entity);
    }

    @Override
    public JerseyReadEntityResponseBuilder<T> read() {
        return new JerseyReadEntityResponseBuilder<>();
    }

    @Override
    public JerseyListEntityResponseBuilder<T> list(Collection<T> list) {
        return new JerseyListEntityResponseBuilder<>(list);
    }
}
