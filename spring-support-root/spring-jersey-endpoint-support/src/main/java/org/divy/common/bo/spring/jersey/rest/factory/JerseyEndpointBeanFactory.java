package org.divy.common.bo.spring.jersey.rest.factory;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.core.factory.DynamicBeansFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;


public class JerseyEndpointBeanFactory implements DynamicBeansFactory {
    
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
