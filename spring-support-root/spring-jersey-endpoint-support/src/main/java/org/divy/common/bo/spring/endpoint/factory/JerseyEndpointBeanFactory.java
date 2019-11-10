package org.divy.common.bo.spring.endpoint.factory;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.core.factory.DynamicBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;


public class JerseyEndpointBeanFactory implements DynamicBeanFactory<Class<? extends BusinessObject>> {
    
    private final BeanNamingStrategy namingStrategy;

    public JerseyEndpointBeanFactory(BeanNamingStrategy namingStrategy) {
        this.namingStrategy = namingStrategy;
    }

    @Override
    public void register(Class<? extends BusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {
    }

    @Override
    public void registerSingletons(BeanDefinitionRegistry beanDefinitionRegistry)
    {
        //NO OPERATION
    }

    protected BeanNamingStrategy getNamingStrategy()
    {
        return namingStrategy;
    }
}
