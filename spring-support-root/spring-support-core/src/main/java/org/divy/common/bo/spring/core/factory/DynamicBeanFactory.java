package org.divy.common.bo.spring.core.factory;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;

public interface DynamicBeanFactory<T> {
    void register(T dynamicBeanFactory, BeanDefinitionRegistry beanDefinitionRegistry);
    void register(BeanDefinitionRegistry beanDefinitionRegistry);

}
