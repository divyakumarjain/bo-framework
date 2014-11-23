package org.divy.common.bo.endpoint.json.test;

/**
 * Created by divyjain on 11/30/2014.
 */
public interface KeyGenerator<ENTITY, ID> {
    ID getRandomKey();

    void initializeKey(ENTITY entity);
}
