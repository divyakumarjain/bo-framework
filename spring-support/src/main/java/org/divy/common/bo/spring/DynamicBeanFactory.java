package org.divy.common.bo.spring;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;

/**
 * Created by divyjain on 12/22/2016.
 */
public interface DynamicBeanFactory<T> {
    void register(T dynamicBeanFactory, BeanDefinitionRegistry beanDefinitionRegistry);
}
