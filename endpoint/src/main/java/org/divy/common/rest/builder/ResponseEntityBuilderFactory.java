package org.divy.common.rest.builder;

import java.util.List;

import javax.inject.Inject;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.rest.RESTEntityURLBuilder;


public class ResponseEntityBuilderFactory<T extends IBusinessObject> {

    @Inject
    RESTEntityURLBuilder entityURLBuilder;

    /* Builder static methods*/

    public CreateEntityResponseBuilder<T> create(T entity){
        final CreateEntityResponseBuilder<T> createEntityResponseBuilder = new CreateEntityResponseBuilder<>(entityURLBuilder);
        createEntityResponseBuilder.setEntityURLBuilder(entityURLBuilder);
        createEntityResponseBuilder.entity(entity);
        return createEntityResponseBuilder;
    }

    public UpdateEntityResponseBuilder<T> update(T entity){
        UpdateEntityResponseBuilder<T> updateEntityResponseBuilder = new UpdateEntityResponseBuilder<>(entity);
        updateEntityResponseBuilder.setEntityURLBuilder(entityURLBuilder);
        return updateEntityResponseBuilder;
    }

    public DeleteEntityResponseBuilder<T> delete(T entity){
        DeleteEntityResponseBuilder<T> deleteEntityResponseBuilder = new DeleteEntityResponseBuilder<>(entity);
        deleteEntityResponseBuilder.setEntityURLBuilder(entityURLBuilder);
        return deleteEntityResponseBuilder;
    }

    public ReadEntityResponseBuilder<T> read(T entity){
        ReadEntityResponseBuilder<T> readEntityResponseBuilder = new ReadEntityResponseBuilder<>(entity);
        readEntityResponseBuilder.setEntityURLBuilder(entityURLBuilder);
        return readEntityResponseBuilder;
    }

    public ResponseEntityBuilder<List<T>> list(List<T> list) {
        ListEntityResponseBuilder<T> listEntityResponseBuilder = new ListEntityResponseBuilder<T>(list);
        return listEntityResponseBuilder;
    }

    public RESTEntityURLBuilder getEntityURLBuilder() {
        return entityURLBuilder;
    }

    public void setEntityURLBuilder(RESTEntityURLBuilder entityURLBuilder) {
        this.entityURLBuilder = entityURLBuilder;
    }
}
