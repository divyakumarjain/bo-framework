package org.divy.common.bo.dynamic.clazz.member.method.reader;

import org.apache.commons.beanutils.PropertyUtils;
import org.divy.common.exception.NotFoundException;
import org.divy.common.exception.UncheckedException;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class AttributeReader implements Reader {
    private String attributeName;

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    @Override
    public Optional<Object> read(Object source, Object... argv) {
        try {

            return Optional.ofNullable(PropertyUtils.getSimpleProperty(source,attributeName));
        } catch (InvocationTargetException ex) {
            throw new UncheckedException("Could not read property "+attributeName, ex);
        } catch (IllegalAccessException|NoSuchMethodException ex){
            throw new NotFoundException(ex);
        }
    }
}
