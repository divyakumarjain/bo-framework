package org.divy.common.bo.endpoint.hypermedia.association;

import java.util.Optional;

@FunctionalInterface
public interface Reader {
    Optional<Object> read(Object source, Object... argv);
}
