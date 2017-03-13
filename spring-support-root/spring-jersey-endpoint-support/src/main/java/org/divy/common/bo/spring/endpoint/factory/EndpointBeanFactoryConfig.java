package org.divy.common.bo.spring.endpoint.factory;

import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointBeanFactoryConfig {


    @Bean
    public EndpointBeanFactory endpointBeanFactory(BeanNamingStrategy namingStrategy) {
        return new EndpointBeanFactory(namingStrategy);
    }

}
