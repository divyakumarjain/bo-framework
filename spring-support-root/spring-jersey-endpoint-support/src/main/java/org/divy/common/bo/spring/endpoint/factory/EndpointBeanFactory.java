package org.divy.common.bo.spring.endpoint.factory;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.core.factory.DynamicBeanFactory;
import org.divy.common.rest.JerseyHyperMediaMapper;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;


public class EndpointBeanFactory implements DynamicBeanFactory<Class<? extends BusinessObject>> {
    
    private final BeanNamingStrategy namingStrategy;

    public EndpointBeanFactory(BeanNamingStrategy namingStrategy) {
        this.namingStrategy = namingStrategy;
    }

    @Override
    public void register(Class<? extends BusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {
        beanDefinitionRegistry.registerBeanDefinition(namingStrategy.calculateHyperMediaMapperId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(JerseyHyperMediaMapper.class)
                        .addConstructorArgReference(namingStrategy.calculateKeyValueMapper(type))
                        .addConstructorArgReference("linkBuilderFactory")
                        .addConstructorArgReference("entityMetaDataProvider")
                        .getBeanDefinition());
    }

    @Override
    public void register(BeanDefinitionRegistry beanDefinitionRegistry)
    {
        //NO OPERATION
    }
}
