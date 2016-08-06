package org.divy.common.rest.builder;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.rest.RESTEntityURLBuilder;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


public class ResponseEntityBuilderFactory<T extends IBusinessObject<I>, I extends Serializable> {

    @Inject
    RESTEntityURLBuilder<T, I> entityURLBuilder;

    /* Builder static methods*/

    public CreateEntityResponseBuilder<T, I> create(T entity) {
        final CreateEntityResponseBuilder<T, I> createEntityResponseBuilder = new CreateEntityResponseBuilder<>(entityURLBuilder);
        createEntityResponseBuilder.setEntityURLBuilder(entityURLBuilder);
        createEntityResponseBuilder.entity(entity);
        return createEntityResponseBuilder;
    }

    public UpdateEntityResponseBuilder<T, I> update(T entity) {
        UpdateEntityResponseBuilder<T, I> updateEntityResponseBuilder = new UpdateEntityResponseBuilder<>(entity);
        updateEntityResponseBuilder.setEntityURLBuilder(entityURLBuilder);
        return updateEntityResponseBuilder;
    }

    public DeleteEntityResponseBuilder<T, I> delete(T entity) {
        DeleteEntityResponseBuilder<T, I> deleteEntityResponseBuilder = new DeleteEntityResponseBuilder<>(entity);
        deleteEntityResponseBuilder.setEntityURLBuilder(entityURLBuilder);
        return deleteEntityResponseBuilder;
    }

    public ReadEntityResponseBuilder<T, I> read(T entity) {
        ReadEntityResponseBuilder<T, I> readEntityResponseBuilder = new ReadEntityResponseBuilder<>(entity);
        readEntityResponseBuilder.setEntityURLBuilder(entityURLBuilder);
        return readEntityResponseBuilder;
    }

    public ResponseEntityBuilder<List<T>> list(List<T> list) {
        ListEntityResponseBuilder<T, I> listEntityResponseBuilder = new ListEntityResponseBuilder<>(list);
        return listEntityResponseBuilder;
    }

    public RESTEntityURLBuilder<T, I> getEntityURLBuilder() {
        return entityURLBuilder;
    }

    public void setEntityURLBuilder(RESTEntityURLBuilder<T, I> entityURLBuilder) {
        this.entityURLBuilder = entityURLBuilder;
    }
}
