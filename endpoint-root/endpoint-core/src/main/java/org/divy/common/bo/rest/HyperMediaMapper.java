package org.divy.common.bo.rest;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.endpoint.hypermedia.Representation;
import org.divy.common.bo.mapper.BOMapper;

import java.util.Collection;
import java.util.Map;

public interface HyperMediaMapper<E extends BusinessObject<?>, R extends Representation>
        extends BOMapper<E, Map<String, Object>> {

    Collection<R> createRepresentationFromBO(Collection<E> boList);
    R createRepresentationFromBO(E businessObject);

    E createBOFromRepresentation(R representation);
    Collection<E> createBOFromRepresentation(Collection<R> representation);


}