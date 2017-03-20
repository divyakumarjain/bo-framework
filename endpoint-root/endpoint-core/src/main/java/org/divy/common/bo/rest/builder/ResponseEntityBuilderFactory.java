package org.divy.common.bo.rest.builder;

import org.divy.common.bo.rest.RESTEntityURLBuilder;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.Collection;


public class ResponseEntityBuilderFactory<T, I extends Serializable> {

    RESTEntityURLBuilder<T, I> entityURLBuilder;

    @Inject
    public ResponseEntityBuilderFactory(RESTEntityURLBuilder<T, I> entityURLBuilder) {
        this.entityURLBuilder = entityURLBuilder;
    }

    /* Builder static methods*/

    public CreateEntityResponseBuilder<T, I> create(T entity) {
        final CreateEntityResponseBuilder<T, I> createEntityResponseBuilder = new CreateEntityResponseBuilder<>(entityURLBuilder);
        createEntityResponseBuilder.setEntityURLBuilder(entityURLBuilder);
        createEntityResponseBuilder.entity(entity);
        return createEntityResponseBuilder;
    }

    public UpdateEntityResponseBuilder<T, I> update() {
        UpdateEntityResponseBuilder<T, I> updateEntityResponseBuilder = new UpdateEntityResponseBuilder<>();
        updateEntityResponseBuilder.setEntityURLBuilder(entityURLBuilder);
        return updateEntityResponseBuilder;
    }

    public DeleteEntityResponseBuilder<T, I> delete(T entity) {
        DeleteEntityResponseBuilder<T, I> deleteEntityResponseBuilder = new DeleteEntityResponseBuilder<>(entity);
        deleteEntityResponseBuilder.setEntityURLBuilder(entityURLBuilder);
        return deleteEntityResponseBuilder;
    }

    public DeleteEntityResponseBuilder<T, I> delete() {
        DeleteEntityResponseBuilder<T, I> deleteEntityResponseBuilder = new DeleteEntityResponseBuilder<>();
        deleteEntityResponseBuilder.setEntityURLBuilder(entityURLBuilder);
        return deleteEntityResponseBuilder;
    }

    public ReadEntityResponseBuilder<T, I> read(T entity) {
        ReadEntityResponseBuilder<T, I> readEntityResponseBuilder = new ReadEntityResponseBuilder<>(entity);
        readEntityResponseBuilder.setEntityURLBuilder(entityURLBuilder);
        return readEntityResponseBuilder;
    }

    public ResponseEntityBuilder<Collection<T>> list(Collection<T> list) {
        return new ListEntityResponseBuilder<>(list);
    }

    public RESTEntityURLBuilder<T, I> getEntityURLBuilder() {
        return entityURLBuilder;
    }

    public void setEntityURLBuilder(RESTEntityURLBuilder<T, I> entityURLBuilder) {
        this.entityURLBuilder = entityURLBuilder;
    }
}
