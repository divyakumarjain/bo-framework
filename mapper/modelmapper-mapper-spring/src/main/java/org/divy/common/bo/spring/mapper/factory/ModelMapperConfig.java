package org.divy.common.bo.spring.mapper.factory;

import org.divy.common.bo.mapper.modelmapper.builder.ModelMapperBuilder;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapperBuilder mapperBuilder() {
        return new ModelMapperBuilder();
    }

    @Bean
    public ModelMapperBeansFactory mapperBeansFactory(BeanNamingStrategy namingStrategy) {
        return new ModelMapperBeansFactory(namingStrategy);
    }

}
