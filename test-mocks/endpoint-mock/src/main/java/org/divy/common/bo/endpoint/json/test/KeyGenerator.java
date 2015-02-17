package org.divy.common.bo.endpoint.json.test;

public interface KeyGenerator<ENTITY, ID> {
    ID getRandomKey();

    void initializeKey(ENTITY entity);
}
