package org.divy.common.bo.spring.endpoint.factory;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.core.factory.DynamicBeanFactory;
import org.divy.common.bo.spring.endpoint.SpringMVCEndPointFactory;
import org.divy.common.rest.SpringMVCHyperMediaMapper;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.Set;


public class SpringMVCEndpointBeanFactory implements DynamicBeanFactory<Class<? extends IBusinessObject>> {
    
    private final BeanNamingStrategy namingStrategy;
    private Set<SpringMVCEndPointFactory> springMVCEndPointFactories;

    public SpringMVCEndpointBeanFactory(BeanNamingStrategy namingStrategy, Set<SpringMVCEndPointFactory> springMVCEndPointFactories) {
        this.namingStrategy = namingStrategy;
        this.springMVCEndPointFactories = springMVCEndPointFactories;
    }

    @Override
    public void register(Class<? extends IBusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {
        beanDefinitionRegistry.registerBeanDefinition(namingStrategy.calculateHyperMediaMapperId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(SpringMVCHyperMediaMapper.class)
                        .addConstructorArgReference(namingStrategy.calculateKeyValueMapper(type))
                        .addConstructorArgReference("linkBuilderFactory")
                        .addConstructorArgReference("entityMetaDataProvider")
                        .getBeanDefinition());

        springMVCEndPointFactories.forEach( factory -> factory.buildEndpointClass(type).ifPresent(endpointClass->
                beanDefinitionRegistry.registerBeanDefinition(endpointClass.getSimpleName().toLowerCase()
                        , BeanDefinitionBuilder.genericBeanDefinition(endpointClass).getBeanDefinition())
        ));
    }
}
