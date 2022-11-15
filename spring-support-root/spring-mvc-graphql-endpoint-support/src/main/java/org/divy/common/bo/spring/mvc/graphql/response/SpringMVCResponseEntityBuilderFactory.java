package org.divy.common.bo.spring.mvc.graphql.response;

import org.divy.common.bo.rest.RESTEntityURLBuilder;
import org.divy.common.bo.rest.response.ListEntityResponseBuilder;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import java.util.Collection;


public class SpringMVCResponseEntityBuilderFactory<T> implements ResponseEntityBuilderFactory<T, ResponseEntity<T>> {

    private RESTEntityURLBuilder<T> entityURLBuilder;

    @Inject
    public SpringMVCResponseEntityBuilderFactory(RESTEntityURLBuilder<T> entityURLBuilder) {
        this.entityURLBuilder = entityURLBuilder;
    }

    /* Builder static methods*/

    @Override
    public SpringMVCCreateEntityResponseBuilder<T> create(T entity) {
        final SpringMVCCreateEntityResponseBuilder<T> createEntityResponseBuilder = new SpringMVCCreateEntityResponseBuilder<>(entityURLBuilder);
        createEntityResponseBuilder.entity(entity);
        return createEntityResponseBuilder;
    }

    @Override
    public SpringMVCUpdateEntityResponseBuilder<T> update() {
        return new SpringMVCUpdateEntityResponseBuilder<>();
    }

    @Override
    public SpringMVCDeleteEntityResponseBuilder<T> delete(T entity) {
        return new SpringMVCDeleteEntityResponseBuilder<>(entity);
    }

    @Override
    public SpringMVCDeleteEntityResponseBuilder<T> delete() {
        return new SpringMVCDeleteEntityResponseBuilder<>();
    }

    @Override
    public SpringMVCReadEntityResponseBuilder<T> read(T entity) {
        return new SpringMVCReadEntityResponseBuilder<>(entity);
    }

    @Override
    public SpringMVCReadEntityResponseBuilder<T> read() {
        return new SpringMVCReadEntityResponseBuilder<>();
    }

    @Override
    public ListEntityResponseBuilder<Collection<T>, ResponseEntity<T>> list(Collection<T> list) {
        return (ListEntityResponseBuilder) new SpringMVCListEntityResponseBuilder<T>(list);
    }
}
