package org.divy.common.bo.spring.mapper.factory;

import org.divy.common.bo.mapper.orika.builder.OrikaMapperBuilder;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrikaMapperConfig {

    @Bean
    public OrikaMapperBuilder mapperBuilder() {
        return new OrikaMapperBuilder();
    }

    @Bean
    public MapperBeansFactory mapperBeansFactory(BeanNamingStrategy namingStrategy) {
        return new MapperBeansFactory(namingStrategy);
    }

}
