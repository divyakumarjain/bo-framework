package org.divy.common.bo.endpoint.hatoas.association;

import java.util.Optional;

public interface Create<T,E> {
    Optional<Object> createWith( T source, E target );
}
