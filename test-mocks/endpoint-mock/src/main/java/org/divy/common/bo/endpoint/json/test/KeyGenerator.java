package org.divy.common.bo.endpoint.json.test;

public interface KeyGenerator<E, I> {
    I getRandomKey();

    void initializeKey(E entity);
}
