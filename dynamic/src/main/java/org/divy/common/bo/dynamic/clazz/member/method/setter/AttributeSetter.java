package org.divy.common.bo.dynamic.clazz.member.method.setter;

import org.apache.commons.beanutils.PropertyUtils;
import org.divy.common.exception.NotFoundException;
import org.divy.common.exception.UncheckedException;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class AttributeSetter<T,E> implements Setter<T,E> {
    private final String attributeName;

    public  AttributeSetter(String attributeName) {
        this.attributeName = attributeName;
    }

    @Override
    public Optional<Object> set(Object source, Object target) {
        try {
            final var oldValue = Optional.ofNullable( PropertyUtils.getSimpleProperty( source, attributeName ) );

            PropertyUtils.setSimpleProperty( source, attributeName,target );

            return oldValue;
        } catch (InvocationTargetException ex) {
            throw new UncheckedException("Could not read property "+attributeName, ex);
        } catch (IllegalAccessException|NoSuchMethodException ex){
            throw new NotFoundException(ex);
        }
    }
}
