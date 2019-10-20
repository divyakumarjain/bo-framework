package org.divy.common.bo.spring.endpoint.factory;

import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.endpoint.JerseyEndpointConfigProperties;
import org.divy.common.rest.JerseyEndPointRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class JerseyEndpointBeanFactoryConfig
{
    @Bean
    @ConditionalOnProperty(value = "bo-framework.endpoint.jersey.enable-hateoas-api", havingValue="true")
    public JerseyHyperMediaJerseyEndpointBeansFactory hyperMediaEndpointBeanFactory(BeanNamingStrategy namingStrategy) {
        return new JerseyHyperMediaJerseyEndpointBeansFactory(namingStrategy);
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
