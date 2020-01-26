package org.divy.common.bo.dynamic.clazz.member.method.setter;

import java.util.Optional;

public interface Setter<T,E> {
    Optional<Object> set( T source, E target );
}
