package org.divy.common.bo.spring;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;

public interface DynamicBeanFactory<T> {
    void register(T dynamicBeanFactory, BeanDefinitionRegistry beanDefinitionRegistry);
}
