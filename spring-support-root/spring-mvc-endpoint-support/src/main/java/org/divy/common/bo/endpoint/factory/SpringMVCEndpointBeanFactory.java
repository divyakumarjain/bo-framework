package org.divy.common.bo.endpoint.factory;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.spring.core.factory.DynamicBeanFactory;
import org.divy.common.bo.endpoint.GlobalControllerExceptionHandler;
import org.divy.common.bo.endpoint.SpringMVCEndPointClassFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.Set;

public class SpringMVCEndpointBeanFactory implements DynamicBeanFactory<Class<? extends BusinessObject>>
{
    private Set<SpringMVCEndPointClassFactory> springMVCEndPointFactories;

    public SpringMVCEndpointBeanFactory(Set<SpringMVCEndPointClassFactory> springMVCEndPointFactories)
    {
        this.springMVCEndPointFactories = springMVCEndPointFactories;
    }

    @Override
    public void register(Class<? extends BusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {
        registerEndpoints(type, beanDefinitionRegistry);

    }

    private void registerEndpoints(Class<? extends BusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry)
    {
        springMVCEndPointFactories.forEach( factory -> factory.buildEndpointClass(type).ifPresent(endpointClass->
                beanDefinitionRegistry.registerBeanDefinition(endpointClass.getSimpleName().toLowerCase()
                        , BeanDefinitionBuilder.genericBeanDefinition(endpointClass).getBeanDefinition())
        ));
    }


    @Override
    public void registerSingletons(BeanDefinitionRegistry beanDefinitionRegistry) {
        beanDefinitionRegistry.registerBeanDefinition("globalControllerExceptionHandler", BeanDefinitionBuilder.genericBeanDefinition(GlobalControllerExceptionHandler.class)
                .getBeanDefinition());
    }
}
