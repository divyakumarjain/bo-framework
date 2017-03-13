package org.divy.common.bo.spring.endpoint.factory;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.core.factory.DynamicBeanFactory;
import org.divy.common.rest.impl.DefaultHATEOASMapper;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;


public class EndpointBeanFactory implements DynamicBeanFactory<Class<? extends IBusinessObject>> {
    
    private final BeanNamingStrategy namingStrategy;

    public EndpointBeanFactory(BeanNamingStrategy namingStrategy) {
        this.namingStrategy = namingStrategy;
    }

    @Override
    public void register(Class<? extends IBusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {
        beanDefinitionRegistry.registerBeanDefinition(namingStrategy.calculateHATEOASMapperId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(DefaultHATEOASMapper.class)
                        .addConstructorArgValue(type)
                        .addConstructorArgReference(namingStrategy.calculateKeyValueMapper(type))
                        .addConstructorArgReference("linkBuilderFactory")
                        .addConstructorArgReference("entityMetaDataProvider")
                        .getBeanDefinition());
    }
}
