package org.divy.common.bo.spring.context;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.database.BoEntityMetaDataProvider;
import org.divy.common.bo.spring.BeanNamingStrategy;
import org.divy.common.bo.spring.BeanNamingStrategyImpl;
import org.divy.common.bo.spring.BoBeansFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@PropertySource( value = {"classpath:/application.properties"})
@ComponentScan(basePackages = "org.divy.mapper.spring")
public class BoFrameworkSpringContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoFrameworkSpringContext.class);

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public BoBeansFactory dynamicBeanFactory(BeanNamingStrategy namingStrategy) {
        return new BoBeansFactory(namingStrategy);
    }

    @Bean
    public BeanNamingStrategyImpl namingStrategy() {
        return new BeanNamingStrategyImpl();
    }

    @Bean
    public BoEntityMetaDataProvider entityMetaDataProvider(@Value("${entityTypes}") String typesList) {
        List<Class<? extends IBusinessObject>> typeList = Arrays.stream(typesList.split(","))
                .map(className -> {
                    try {
                        return (Class<? extends IBusinessObject>) Class.forName(className);
                    } catch (ClassNotFoundException e) {
                        LOGGER.error("Entity Class not found :" + className,e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new BoEntityMetaDataProvider(typeList);
    }
}
