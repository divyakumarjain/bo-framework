package org.divy.common.bo.mapper.defaults;

import org.dozer.BeanFactory;

import java.util.UUID;

/**
 * Created by divyjain on 9/25/2016.
 */
public class UuidBeanFactory implements BeanFactory {
    @Override
    public Object createBean(Object sourceBean, Class<?> destinationType, String mapId) {
        if (sourceBean == null) {
            return null;
        }
        UUID source = (UUID) sourceBean;
        UUID destination = new UUID(source.getMostSignificantBits(), source.getLeastSignificantBits());
        return destination;
    }
}
