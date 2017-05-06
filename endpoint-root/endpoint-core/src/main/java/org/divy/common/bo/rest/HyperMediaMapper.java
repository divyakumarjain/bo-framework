package org.divy.common.bo.rest;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.endpoint.hypermedia.Representation;
import org.divy.common.bo.mapper.IBOMapper;

import java.util.Collection;
import java.util.Map;

public interface HyperMediaMapper<E extends IBusinessObject<?>, R extends Representation>
        extends IBOMapper<E, Map<String, Object>> {

    Collection<R> createRepresentationFromBO(Collection<E> boList);
    R createRepresentationFromBO(E businessObject);

    E createBOFromRepresentation(R representation);
    Collection<E> createBOFromRepresentation(Collection<R> representation);


}
