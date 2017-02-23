package org.divy.common.bo.spring.context;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.database.BoEntityMetaDataProvider;
import org.divy.common.bo.mapper.builder.MapperBuilder;
import org.divy.common.bo.mapper.orika.builder.OrikaMapperBuilder;
import org.divy.common.bo.spring.BoBeansFactory;
import org.divy.common.bo.spring.DynamicBeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@PropertySource( value = {"classpath:/application.properties"})
public class BoFrameworkSpringContext {

    static final private Logger LOGGER = LoggerFactory.getLogger(BoFrameworkSpringContext.class);

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DynamicBeanFactory<Class<? extends IBusinessObject>> dynamicBeanFactory() {
        return new BoBeansFactory();
    }

    @Bean
    BoEntityMetaDataProvider entityMetaDataProvider(@Value("${entityTypes}") String typesList) {
        List<Class<? extends IBusinessObject>> typeList = Arrays.stream(typesList.split(","))
                .map(className -> {
                    try {
                        return (Class<? extends IBusinessObject>) this.getClass().forName(className);
                    } catch (ClassNotFoundException e) {
                        LOGGER.error("Entity Class not found :" + className,e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new BoEntityMetaDataProvider(typeList);
    }

    @Bean
    MapperBuilder mapperBuilder() {
        return new OrikaMapperBuilder();
    }
}
