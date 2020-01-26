package org.divy.common.bo.spring.jersey.rest.factory;

import org.divy.common.bo.jersey.rest.JerseyEndPointRegistry;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.jersey.rest.JerseyEndpointConfigProperties;
import org.divy.common.bo.spring.jersey.rest.hatoas.JerseyHyperMediaEndpointBeansFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyEndpointBeanFactoryConfig
{
    @Bean
    @ConditionalOnProperty(value = "bo-framework.endpoint.jersey.enable-hateoas-api", havingValue="true")
    public JerseyHyperMediaEndpointBeansFactory hyperMediaEndpointBeanFactory(BeanNamingStrategy namingStrategy) {
        return new JerseyHyperMediaEndpointBeansFactory(namingStrategy);
    }

    @Bean
    @ConditionalOnProperty(value = "bo-framework.endpoint.jersey.enable-hateoas-api", havingValue="false")
    public JerseyEndpointBeanFactory mvcEndPointBeanFactory(BeanNamingStrategy namingStrategy) {
        return new JerseyEndpointBeanFactory(namingStrategy);
    }

    @Bean
    public JerseyEndPointRegistry mvcEndPointRegistry() {
        return new JerseyEndPointRegistry();
    }

    @Bean
    public JerseyEndpointConfigProperties mvcEndpointConfigProperties() {
        return new JerseyEndpointConfigProperties();
    }
}
