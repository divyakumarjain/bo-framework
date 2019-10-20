package org.divy.common.bo.spring.endpoint.factory;

import org.divy.common.bo.endpoint.jersey.hypermedia.association.JerseyAssociationsHandler;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.rest.JerseyHyperMediaMapper;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

public class JerseyHyperMediaJerseyEndpointBeansFactory extends JerseyEndpointBeanFactory
{

    public JerseyHyperMediaJerseyEndpointBeansFactory( BeanNamingStrategy namingStrategy) {
        super(namingStrategy);
    }

    @Override
    public void register(Class<? extends BusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {

        super.register( type, beanDefinitionRegistry );
        registerHyperMediaMapper(type, beanDefinitionRegistry);
        registerAssociationsHandler(type, beanDefinitionRegistry);
    }

    @Override public void registerSingletons( BeanDefinitionRegistry beanDefinitionRegistry )
    {
    }

    private void registerHyperMediaMapper(Class<? extends BusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry)
    {
        beanDefinitionRegistry.registerBeanDefinition(getNamingStrategy().calculateHyperMediaMapperId(type)
                , BeanDefinitionBuilder.genericBeanDefinition( JerseyHyperMediaMapper.class)
                        .addConstructorArgReference(getNamingStrategy().calculateKeyValueMapper(type))
                        .addConstructorArgReference("linkBuilderFactory")
                        .addConstructorArgReference("entityMetaDataProvider")
                        .getBeanDefinition());
    }

    private void registerAssociationsHandler(Class<? extends BusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry)
    {
        beanDefinitionRegistry.registerBeanDefinition(getNamingStrategy().calculateAssociationsHandler(type)
                , BeanDefinitionBuilder.genericBeanDefinition( JerseyAssociationsHandler.class)
                        .addConstructorArgValue(type)
                        .addConstructorArgReference("entityMetaDataProvider")
                        .addConstructorArgReference("linkBuilderFactory")
                        .addConstructorArgReference("mapperBuilder")
                        .getBeanDefinition());
    }
}
