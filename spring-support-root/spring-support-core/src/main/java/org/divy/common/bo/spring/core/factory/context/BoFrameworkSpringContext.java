package org.divy.common.bo.spring.core.factory.context;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategyImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
@PropertySource( value = {"classpath:/application.properties"})
@ComponentScan(basePackages = {"org.divy.common.bo.spring.mapper.factory"
        , "org.divy.common.bo.spring.endpoint.factory"
        , "org.divy.common.bo.spring.repository.factory"})
public class BoFrameworkSpringContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoFrameworkSpringContext.class);

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public BeanNamingStrategy namingStrategy() {
        return new BeanNamingStrategyImpl();
    }

    @Bean
    public MetaDataProvider entityMetaDataProvider(@Value("${entityTypes}") String typesList) {
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

        return createMetadataProvider(typeList);
    }

    private MetaDataProvider createMetadataProvider(List<Class<? extends IBusinessObject>> typeList) {
        return loadMetaDataProvider("org.divy.common.bo.database.jpa.BoEntityMetaDataProvider", typeList)
                .orElseGet(loadMetaDataProvider("", typeList)::get);
    }

    private Optional<MetaDataProvider> loadMetaDataProvider(String className, List<Class<? extends IBusinessObject>> typeList) {
        Class<?> metaDataProviderClass = null;
        try {
            metaDataProviderClass = Class.forName(className);
            return Optional.of((MetaDataProvider)metaDataProviderClass.getConstructor(List.class).newInstance(typeList));
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            LOGGER.warn("Could not load Meta data provider " + className);
            return Optional.empty();
        }
    }
}