package org.divy.common.bo.spring.endpoint.factory;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.spring.endpoint.hypermedia.association.SpringAssociationsHandler;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.endpoint.SpringMVCEndPointClassFactory;
import org.divy.common.rest.SpringMVCHyperMediaMapper;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.Set;


public class SpringMVCHyperMediaEndpointBeansFactory extends SpringMVCEndpointBeansFactory
{
    
    private final BeanNamingStrategy namingStrategy;

    public SpringMVCHyperMediaEndpointBeansFactory( BeanNamingStrategy namingStrategy, Set<SpringMVCEndPointClassFactory> springMVCEndPointFactories,
          EndPointRegistry endPointRegistry ) {
        super(springMVCEndPointFactories, endPointRegistry );
        this.namingStrategy = namingStrategy;
    }

    @Override
    public void register(Class<? extends BusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {
        super.register(type, beanDefinitionRegistry);

        registerHyperMediaMapper(type, beanDefinitionRegistry);
        registerAssociationsHandler(type, beanDefinitionRegistry);
    }

    private void registerHyperMediaMapper(Class<? extends BusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry)
    {
        beanDefinitionRegistry.registerBeanDefinition(namingStrategy.calculateHyperMediaMapperId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(SpringMVCHyperMediaMapper.class)
                        .addConstructorArgReference(namingStrategy.calculateKeyValueMapper(type))
                        .addConstructorArgReference("linkBuilderFactory")
                        .addConstructorArgReference("entityMetaDataProvider")
                        .getBeanDefinition());
    }

    private void registerAssociationsHandler(Class<? extends BusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry)
    {
        beanDefinitionRegistry.registerBeanDefinition(namingStrategy.calculateAssociationsHandler(type)
                , BeanDefinitionBuilder.genericBeanDefinition( SpringAssociationsHandler.class)
                        .addConstructorArgValue(type)
                        .addConstructorArgReference("entityMetaDataProvider")
                        .addConstructorArgReference("linkBuilderFactory")
                        .addConstructorArgReference("mapperBuilder")
                        .getBeanDefinition());
    }
}