package org.divy.common.bo.spring.endpoint.factory;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.spring.core.factory.DynamicBeanFactory;
import org.divy.common.bo.spring.endpoint.GlobalControllerExceptionHandler;
import org.divy.common.bo.spring.endpoint.SpringMVCEndPointClassFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class SpringMVCEndpointBeansFactory implements DynamicBeanFactory<Class<? extends BusinessObject>> {

    static private final Logger LOGGER = LoggerFactory.getLogger( SpringMVCEndpointBeansFactory.class );
    private final EndPointRegistry endPointRegistry;
    private Set<SpringMVCEndPointClassFactory> springMVCEndPointFactories;

    public SpringMVCEndpointBeansFactory( Set<SpringMVCEndPointClassFactory> springMVCEndPointFactories, EndPointRegistry endPointRegistry )
    {
        this.springMVCEndPointFactories = springMVCEndPointFactories;
        this.endPointRegistry = endPointRegistry;
    }

    @Override
    public void register(Class<? extends BusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {
        registerEndpoints(type, beanDefinitionRegistry);

    }

    private void registerEndpoints(Class<? extends BusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry)
    {
        springMVCEndPointFactories.forEach( factory -> factory.buildEndpointClass(type).ifPresent(endpointConfigs-> {
            for( Map.Entry<String, Class<?>> configEntry : endpointConfigs.entrySet() ) {

                Class<?> endpointClass = configEntry.getValue();
                String endPointUrl = configEntry.getKey();
                String endPointBeanName = endpointClass.getSimpleName().toLowerCase();

                endPointRegistry.addEntityEndPointMap( type.getSimpleName(), endpointClass );

                LOGGER.debug( "Registering a class {} at {}", endpointClass.getSimpleName(), endPointUrl );

                beanDefinitionRegistry.registerBeanDefinition( endPointBeanName,
                BeanDefinitionBuilder.genericBeanDefinition( endpointClass)
                      .getBeanDefinition());

                Properties mappingProperties = new Properties();

                mappingProperties.put( endPointUrl, endPointBeanName );

                beanDefinitionRegistry.registerBeanDefinition( endPointBeanName +"Mapper"
                      , BeanDefinitionBuilder.genericBeanDefinition( "org.springframework.web.servlet.handler.SimpleUrlHandlerMapping" )
                            .addPropertyValue("mappings", mappingProperties )
                            .addPropertyValue( "order", 3 )
                            .getBeanDefinition());
            }
        }));
    }


    @Override
    public void registerSingletons(BeanDefinitionRegistry beanDefinitionRegistry) {
        beanDefinitionRegistry.registerBeanDefinition("globalControllerExceptionHandler", BeanDefinitionBuilder.genericBeanDefinition(GlobalControllerExceptionHandler.class)
                .getBeanDefinition());
    }
}
