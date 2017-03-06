package org.divy.mapper.spring;

import org.divy.common.bo.mapper.orika.builder.OrikaMapperBuilder;
import org.divy.common.bo.spring.BeanNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrikaMapperConfig {

    @Bean
    public OrikaMapperBuilder mapperBuilder() {
        return new OrikaMapperBuilder();
    }

    @Bean
    public MapperBeansFactoryImpl mapperBeansFactory(BeanNamingStrategy namingStrategy) {
        return new MapperBeansFactoryImpl(namingStrategy);
    }

}
