package org.divy.common.bo.endpoint.association;

import org.divy.common.bo.endpoint.hypermedia.AbstractRepresentation;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by divyjain on 2/17/2015.
 */
public class AttributeReader extends ReaderBuilder {
    private String attributeName;

    @Override
    public AbstractRepresentation read(Object source, Object... argv) throws InvocationTargetException, IllegalAccessException {
        return null;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
}
