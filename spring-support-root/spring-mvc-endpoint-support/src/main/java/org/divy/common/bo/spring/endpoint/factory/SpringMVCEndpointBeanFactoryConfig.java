package org.divy.common.bo.spring.endpoint.factory;

import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.endpoint.SpringMVCEndPointClassFactory;
import org.divy.common.bo.spring.endpoint.SpringMVCEndpointConfigProperties;
import org.divy.common.bo.spring.endpoint.SpringMVCHyperMediaEndPointClassFactory;
import org.divy.common.rest.SpringMVCEndPointRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class SpringMVCEndpointBeanFactoryConfig {

    @Bean
    @ConditionalOnProperty(value = "bo-framework.endpoint.mvc.enable-hateoas-api", havingValue="true")
    public SpringMVCHyperMediaEndPointClassFactory mvcHyperMediaEndPointFactory(BeanNamingStrategy beanNamingStrategy
            , SpringMVCEndpointConfigProperties endpointConfigProperties) {

        return new SpringMVCHyperMediaEndPointClassFactory(beanNamingStrategy
                , mvcEndPointRegistry()
                , endpointConfigProperties);
    }

    @Bean
    @ConditionalOnProperty(value = "bo-framework.endpoint.mvc.enable-hateoas-api", havingValue="true")
    public SpringMVCHyperMediaEndpointBeansFactory hyperMediaEndpointBeanFactory(BeanNamingStrategy namingStrategy
            , Set<SpringMVCEndPointClassFactory> springMVCEndPointFactories
            , EndPointRegistry endPointRegistry) {
        return new SpringMVCHyperMediaEndpointBeansFactory(namingStrategy, springMVCEndPointFactories, endPointRegistry );
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringMVCEndPointClassFactory mvcEndPointClassFactory(BeanNamingStrategy beanNamingStrategy
            , SpringMVCEndpointConfigProperties endpointConfigProperties) {
        return new SpringMVCEndPointClassFactory(beanNamingStrategy
                , mvcEndPointRegistry(), endpointConfigProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringMVCEndpointBeansFactory mvcEndPointBeanFactory(
            Set<SpringMVCEndPointClassFactory> springMVCEndPointFactories
    , EndPointRegistry endPointRegistry ) {
        return new SpringMVCEndpointBeansFactory(springMVCEndPointFactories, endPointRegistry);
    }

    @Bean
    public SpringMVCEndPointRegistry mvcEndPointRegistry() {
        return new SpringMVCEndPointRegistry();
    }

    @Bean
    public SpringMVCEndpointConfigProperties mvcEndpointConfigProperties() {
        return new SpringMVCEndpointConfigProperties();
    }
}
