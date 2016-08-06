package org.divy.common.bo.endpoint.association.builder;


import org.divy.common.bo.endpoint.association.AttributeReader;
import org.divy.common.bo.endpoint.association.Reader;

import java.lang.reflect.InvocationTargetException;


public class ReaderBuilder implements Reader {

    protected Reader reader;

    @Override
    public Object read(Object source, Object... argv) throws InvocationTargetException, IllegalAccessException {
        Object result = getReader().read(source, argv);
        return result;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public <T> T withMethodOn(Class<T> groupClass) {
        DynamicReader dynamicReader = new DynamicReader();
        setReader(dynamicReader);
        return dynamicReader.withMethodOn(groupClass);
    }

    public AttributeReader attribute(String attributeName) {
        AttributeReader attributeReader = new AttributeReader();
        setReader(attributeReader);
        attributeReader.setAttributeName(attributeName);
        return attributeReader;
    }
}

