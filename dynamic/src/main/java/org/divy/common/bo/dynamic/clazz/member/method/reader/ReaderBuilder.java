package org.divy.common.bo.dynamic.clazz.member.method.reader;

import org.divy.common.bo.dynamic.clazz.Builder;

import java.util.Optional;


public class ReaderBuilder<P extends Builder> implements Reader, Builder {

    protected Reader reader;
    private P parent;

    public ReaderBuilder(P parent)
    {
        this.parent = parent;
    }

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
        setReader(attributeReader);
        return attributeReader;
    }

    @Override public P and()
    {
        return this.parent;
    }
}

