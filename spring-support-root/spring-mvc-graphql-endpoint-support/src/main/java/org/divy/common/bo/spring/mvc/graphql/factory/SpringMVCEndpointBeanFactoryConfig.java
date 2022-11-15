package org.divy.common.bo.spring.mvc.graphql.factory;

import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.mvc.graphql.config.SpringMVCEndpointConfigProperties;
import org.divy.common.bo.spring.mvc.rest.SpringMVCEndPointRegistry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class SpringMVCEndpointBeanFactoryConfig {

    @Bean
    @ConditionalOnProperty(value = "bo-framework.endpoint.mvc.enable-hateoas-api", havingValue="true")
    public SpringMVCHATOASEndPointClassFactory mvcHATOASEndPointFactory(BeanNamingStrategy beanNamingStrategy
            , @Qualifier("entityMetaDataProvider") MetaDataProvider metaDataProvider
            , EndPointRegistry mvcEndPointRegistry
            , SpringMVCEndpointConfigProperties endpointConfigProperties) {

        return new SpringMVCHATOASEndPointClassFactory(beanNamingStrategy
                , mvcEndPointRegistry
                , endpointConfigProperties);
    }

    @Bean
    @ConditionalOnProperty(value = "bo-framework.endpoint.mvc.enable-hateoas-api", havingValue="true")
    public SpringMVCHATOASEndpointBeansFactory hatoasEndpointBeansFactory(BeanNamingStrategy namingStrategy
            , Set<SpringMVCEndPointClassFactory> springMVCEndPointFactories) {
        return new SpringMVCHATOASEndpointBeansFactory(namingStrategy, springMVCEndPointFactories, mvcEndPointRegistry());
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringMVCEndPointClassFactory mvcEndPointClassFactory(BeanNamingStrategy beanNamingStrategy
            , EndPointRegistry mvcEndPointRegistry
            , SpringMVCEndpointConfigProperties endpointConfigProperties) {

        return new SpringMVCEndPointClassFactory(beanNamingStrategy
              , mvcEndPointRegistry
              , endpointConfigProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringMVCEndpointBeansFactory mvcEndPointBeansFactory(
            Set<SpringMVCEndPointClassFactory> springMVCEndPointFactories) {
        return new SpringMVCEndpointBeansFactory(springMVCEndPointFactories, mvcEndPointRegistry());
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
