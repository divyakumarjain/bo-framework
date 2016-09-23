package org.divy.common.bo.endpoint.association;

import java.util.Optional;

public class AttributeReader implements Reader {
    private String attributeName;

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    @Override
    public Optional<Object> read(Object source, Object... argv) {
        return Optional.empty();
    }
}
