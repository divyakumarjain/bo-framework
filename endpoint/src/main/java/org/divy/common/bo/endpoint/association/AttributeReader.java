package org.divy.common.bo.endpoint.association;

import org.divy.common.bo.endpoint.hypermedia.AbstractRepresentation;

import java.lang.reflect.InvocationTargetException;

public class AttributeReader implements Reader {
    private String attributeName;

    @Override
    public AbstractRepresentation read(Object source, Object... argv) throws InvocationTargetException, IllegalAccessException {
        return null;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
}
