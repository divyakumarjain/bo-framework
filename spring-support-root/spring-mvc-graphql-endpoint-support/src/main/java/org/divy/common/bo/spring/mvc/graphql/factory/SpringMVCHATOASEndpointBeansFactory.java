package org.divy.common.bo.spring.mvc.graphql.factory;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.mvc.graphql.hatoas.SpringMVCHATOASMapper;
import org.divy.common.bo.spring.mvc.graphql.hatoas.association.SpringAssociationsHandler;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.Set;


public class SpringMVCHATOASEndpointBeansFactory extends org.divy.common.bo.spring.mvc.graphql.factory.SpringMVCEndpointBeansFactory
{
    
    private final BeanNamingStrategy namingStrategy;

    public SpringMVCHATOASEndpointBeansFactory( BeanNamingStrategy namingStrategy
          , Set<SpringMVCEndPointClassFactory> springMVCEndPointFactories,
          EndPointRegistry endPointRegistry ) {
        super(springMVCEndPointFactories, endPointRegistry );
        this.namingStrategy = namingStrategy;
    }

    @Override
    public void register(Class<? extends BusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {
        super.register(type, beanDefinitionRegistry);

        registerHATOASMapper(type, beanDefinitionRegistry);
        registerAssociationsHandler(type, beanDefinitionRegistry);
    }

    private void registerHATOASMapper(Class<? extends BusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry)
    {
        beanDefinitionRegistry.registerBeanDefinition(namingStrategy.calculateHATOASMapperId(type)
                , BeanDefinitionBuilder.genericBeanDefinition( SpringMVCHATOASMapper.class)
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
