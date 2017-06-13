package org.divy.common.bo.spring.endpoint.factory;

import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.endpoint.SpringMVCEndPointFactory;
import org.divy.common.bo.spring.endpoint.SpringMVCEndpointConfigProperties;
import org.divy.common.bo.spring.endpoint.SpringMVCHyperMediaEndPointFactory;
import org.divy.common.rest.SpringMVCEndPointRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class SpringMVCEndpointBeanFactoryConfig {


    @Bean
    public SpringMVCEndpointBeanFactory endpointBeanFactory(BeanNamingStrategy namingStrategy
            , Set<SpringMVCEndPointFactory> springMVCEndPointFactories) {
        return new SpringMVCEndpointBeanFactory(namingStrategy, springMVCEndPointFactories);
    }

    @Bean
    @ConditionalOnProperty(value = "bo-framework.endpoint.mvc.enable-hateoas-api", havingValue="true")
    public SpringMVCHyperMediaEndPointFactory mvcHyperMediaEndPointFactory(BeanNamingStrategy beanNamingStrategy
            , SpringMVCEndpointConfigProperties endpointConfigProperties) {

        return new SpringMVCHyperMediaEndPointFactory(beanNamingStrategy
                , mvcHyperMediaEndPointRegistry()
                , endpointConfigProperties);
    }

    @Bean
    public SpringMVCEndPointFactory mvcEndPointFactory(BeanNamingStrategy beanNamingStrategy
            , SpringMVCEndpointConfigProperties endpointConfigProperties) {
        return new SpringMVCEndPointFactory(beanNamingStrategy
                , mvcEndPointRegistry(), endpointConfigProperties);
    }

    @Bean
    public SpringMVCEndPointRegistry mvcEndPointRegistry() {
        return new SpringMVCEndPointRegistry();
    }

    @Bean
    public SpringMVCEndPointRegistry mvcHyperMediaEndPointRegistry() {
        return new SpringMVCEndPointRegistry();
    }

    @Bean
    public SpringMVCEndpointConfigProperties mvcEndpointConfigProperties() {
        return new SpringMVCEndpointConfigProperties();
    }
}
