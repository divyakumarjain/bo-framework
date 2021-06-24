package org.divy.common.bo.dynamic.clazz.member.method.setter;

import java.util.Optional;

public interface Setter<T,V> {
    Optional<Object> set( T target, V value );
}
