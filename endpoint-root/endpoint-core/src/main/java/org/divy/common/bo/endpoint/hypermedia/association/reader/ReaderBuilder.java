package org.divy.common.bo.endpoint.hypermedia.association.reader;


import org.divy.common.bo.endpoint.hypermedia.association.AttributeReader;
import org.divy.common.bo.endpoint.hypermedia.association.Reader;

import java.util.Optional;


public class ReaderBuilder implements Reader {

    protected Reader reader;

    @Override
    public Optional<Object> read(Object source, Object... argv) {
        return getReader().read(source, argv);
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

