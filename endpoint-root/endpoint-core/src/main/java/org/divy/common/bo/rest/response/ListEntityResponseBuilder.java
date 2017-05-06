package org.divy.common.bo.rest.response;

public abstract class ListEntityResponseBuilder<E, R> extends AbstractResponseEntityBuilder<E, R> {

    public ListEntityResponseBuilder(E list) {
        setEntity(list);
    }
}
