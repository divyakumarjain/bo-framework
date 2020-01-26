package org.divy.common.bo.spring.core.factory;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;

public interface DynamicBeansFactory<T> {
    void register(T dynamicBeanFactory, BeanDefinitionRegistry beanDefinitionRegistry);
    void registerSingletons(BeanDefinitionRegistry beanDefinitionRegistry);

}
