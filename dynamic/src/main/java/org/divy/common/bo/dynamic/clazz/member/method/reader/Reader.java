package org.divy.common.bo.dynamic.clazz.member.method.reader;

import java.util.Optional;

@FunctionalInterface
public interface Reader {
    Optional<Object> read(Object source, Object... argv);
}
