package org.divy.common.rest.builder;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.rest.RESTEntityURLBuilder;


public class ResponseEntityBuilderFactory<T extends IBusinessObject<ID>,ID extends Serializable> {

    @Inject
    RESTEntityURLBuilder<T,ID> entityURLBuilder;

    /* Builder static methods*/

    public CreateEntityResponseBuilder<T,ID> create(T entity){
        final CreateEntityResponseBuilder<T,ID> createEntityResponseBuilder = new CreateEntityResponseBuilder<>(entityURLBuilder);
        createEntityResponseBuilder.setEntityURLBuilder(entityURLBuilder);
        createEntityResponseBuilder.entity(entity);
        return createEntityResponseBuilder;
    }

    public UpdateEntityResponseBuilder<T,ID> update(T entity){
        UpdateEntityResponseBuilder<T,ID> updateEntityResponseBuilder = new UpdateEntityResponseBuilder<>(entity);
        updateEntityResponseBuilder.setEntityURLBuilder(entityURLBuilder);
        return updateEntityResponseBuilder;
    }

    public DeleteEntityResponseBuilder<T,ID> delete(T entity){
        DeleteEntityResponseBuilder<T,ID> deleteEntityResponseBuilder = new DeleteEntityResponseBuilder<>(entity);
        deleteEntityResponseBuilder.setEntityURLBuilder(entityURLBuilder);
        return deleteEntityResponseBuilder;
    }

    public ReadEntityResponseBuilder<T,ID> read(T entity){
        ReadEntityResponseBuilder<T,ID> readEntityResponseBuilder = new ReadEntityResponseBuilder<>(entity);
        readEntityResponseBuilder.setEntityURLBuilder(entityURLBuilder);
        return readEntityResponseBuilder;
    }

    public ResponseEntityBuilder<List<T>> list(List<T> list) {
        ListEntityResponseBuilder<T,ID> listEntityResponseBuilder = new ListEntityResponseBuilder<>(list);
        return listEntityResponseBuilder;
    }

    public RESTEntityURLBuilder<T,ID> getEntityURLBuilder() {
        return entityURLBuilder;
    }

    public void setEntityURLBuilder(RESTEntityURLBuilder<T,ID> entityURLBuilder) {
        this.entityURLBuilder = entityURLBuilder;
    }
}
