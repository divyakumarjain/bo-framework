package org.divy.common.bo.endpoint.association;

import java.util.Optional;

@FunctionalInterface
public interface Reader {
    Optional<Object> read(Object source, Object... argv);
}
