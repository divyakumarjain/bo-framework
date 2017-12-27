package org.divy.common.bo.endpoint.factory;

import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.endpoint.SpringMVCEndPointClassFactory;
import org.divy.common.bo.endpoint.SpringMVCEndpointConfigProperties;
import org.divy.common.bo.endpoint.SpringMVCHyperMediaEndPointClassFactory;
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
                , mvcHyperMediaEndPointRegistry()
                , endpointConfigProperties);
    }

    @Bean
    @ConditionalOnProperty(value = "bo-framework.endpoint.mvc.enable-hateoas-api", havingValue="true")
    public SpringMVCHyperMediaEndpointBeanFactory hyperMediaEndpointBeanFactory(BeanNamingStrategy namingStrategy
            , Set<SpringMVCEndPointClassFactory> springMVCEndPointFactories) {
        return new SpringMVCHyperMediaEndpointBeanFactory(namingStrategy, springMVCEndPointFactories);
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
    public SpringMVCEndpointBeanFactory mvcEndPointBeanFactory(
            Set<SpringMVCEndPointClassFactory> springMVCEndPointFactories) {
        return new SpringMVCEndpointBeanFactory(springMVCEndPointFactories);
    }

    @Bean
    public SpringMVCEndPointRegistry mvcEndPointRegistry() {
        return new SpringMVCEndPointRegistry();
    }

    @Bean
    @ConditionalOnProperty(value = "bo-framework.endpoint.mvc.enable-hateoas-api", havingValue="true")
    public SpringMVCEndPointRegistry mvcHyperMediaEndPointRegistry() {
        return new SpringMVCEndPointRegistry();
    }

    @Bean
    public SpringMVCEndpointConfigProperties mvcEndpointConfigProperties() {
        return new SpringMVCEndpointConfigProperties();
    }
}
