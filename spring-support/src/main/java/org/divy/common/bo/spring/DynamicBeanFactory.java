package org.divy.common.bo.spring;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;

@FunctionalInterface
public interface DynamicBeanFactory<T> {
    void register(T dynamicBeanFactory, BeanDefinitionRegistry beanDefinitionRegistry);
}
