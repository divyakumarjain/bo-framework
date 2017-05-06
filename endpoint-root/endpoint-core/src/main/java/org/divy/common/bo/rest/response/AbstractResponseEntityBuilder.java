package org.divy.common.bo.rest.response;

public abstract class AbstractResponseEntityBuilder<T, R> implements ResponseEntityBuilder<R> {
    protected T entity;

    protected AbstractResponseEntityBuilder(T entity) {
        setEntity(entity);
    }

    protected AbstractResponseEntityBuilder() {
    }


    public AbstractResponseEntityBuilder<T, R> entity(T entity) {
        this.entity = entity;
        return this;
    }

    /* Getter Setters methods*/


    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

}
