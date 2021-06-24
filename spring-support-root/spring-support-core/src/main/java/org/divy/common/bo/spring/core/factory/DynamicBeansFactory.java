package org.divy.common.bo.spring.core.factory;

import org.divy.common.bo.repository.BusinessObject;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

public interface DynamicBeansFactory {
    void register(Class<? extends BusinessObject> dynamicBeanFactory, BeanDefinitionRegistry beanDefinitionRegistry);
    void registerSingletons(BeanDefinitionRegistry beanDefinitionRegistry);

}
