package org.divy.common.bo.endpoint.association;

import org.divy.common.rest.impl.AbstractHATEOASMapper;

import java.lang.reflect.InvocationTargetException;


public class ReaderBuilder implements Reader {

    protected AbstractHATEOASMapper mapper;

    protected Reader reader;


    public ReaderBuilder withMapper(AbstractHATEOASMapper mapper) {
        this.mapper = mapper;
        return this;
    }

    public AttributeReader attribute(String attributeName) {
        AttributeReader attributeReader = new AttributeReader();
        setReader(attributeReader);
        attributeReader.setAttributeName(attributeName);
        return attributeReader;
    }

    @Override
    public Object read(Object source, Object... argv) throws InvocationTargetException, IllegalAccessException {
        return getReader().read(source,argv);
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
}

