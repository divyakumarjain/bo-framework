package org.divy.common.bo.spring.repository.factory;

import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryBeansFactoryConfig {

    @Bean
    public RepositoryBeansFactory repositoryBeansFactory(BeanNamingStrategy namingStrategy) {
        return new RepositoryBeansFactory(namingStrategy);
    }
}
