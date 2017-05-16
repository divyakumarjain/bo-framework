package org.divy.common.bo.mapper;

import java.util.Collection;

public interface BOMapper<S, T> {

    S createBO(T sourceData);
    S mapToBO(T sourceData, S businessObject);

    T createFromBO(S businessObject);
    T mapFromBO(S businessObject, T targetData);

    Collection<S> createBO(Collection<T> sourceData);

    Collection<T> createFromBO(Collection<S> businessObject);
}
