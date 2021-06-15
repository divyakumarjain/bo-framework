package org.divy.common.bo.rest.response;

public abstract class ListEntityResponseBuilder<E, R> extends AbstractResponseEntityBuilder<E, R> {

    protected ListEntityResponseBuilder(E list) {
        setEntity(list);
    }
}
