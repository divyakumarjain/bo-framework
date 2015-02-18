package org.divy.common.bo.endpoint.association;

import org.divy.common.rest.impl.AbstractHATEOASMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;


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
        Object result = getReader().read(source, argv);

        if(mapper != null) {
            if(result instanceof Collection) {
                return mapper.createFromBO((Collection)result);
            }
            return mapper.createFromBO(result);
        } else {
            return result;
        }
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

