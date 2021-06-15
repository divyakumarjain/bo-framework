package org.divy.common.bo.dynamic.clazz.member.method.setter;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class SetterBuilder<T,E> implements Setter<T,E>
{
    private String attributeName;

    public <O> O withMethodOn( Class<O> groupClass )
    {
        return null;
    }

    @Override
    public Optional<Object> set( T source, E target )
    {
        try
        {
            return Optional.ofNullable( PropertyUtils.getSimpleProperty(source,attributeName));
        }
        catch( IllegalAccessException|InvocationTargetException|NoSuchMethodException e )
        {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void setAttributeName( String attributeName )
    {
        this.attributeName = attributeName;
    }
}
