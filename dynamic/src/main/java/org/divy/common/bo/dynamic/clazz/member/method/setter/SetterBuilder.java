package org.divy.common.bo.dynamic.clazz.member.method.setter;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class SetterBuilder<T,V> implements Setter<T,V>
{
    private static final Logger logger = LoggerFactory.getLogger( SetterBuilder.class );
    private String attributeName;

    public <O> O withMethodOn( Class<O> groupClass )
    {
        return null;
    }

    @Override
    public Optional<Object> set( T target, V value )
    {
        try
        {
            var result = Optional.ofNullable( PropertyUtils.getSimpleProperty(target,attributeName));
            PropertyUtils.setSimpleProperty(target,attributeName, value);
            return result;
        }
        catch( IllegalAccessException|InvocationTargetException|NoSuchMethodException e )
        {
            logger.error("Could not set the value", e );
        }
        return Optional.empty();
    }

    public void setAttributeName( String attributeName )
    {
        this.attributeName = attributeName;
    }
}
