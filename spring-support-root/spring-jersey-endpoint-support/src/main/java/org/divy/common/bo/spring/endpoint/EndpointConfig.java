package org.divy.common.bo.spring.endpoint;


import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.rest.LinkBuilderFactoryImpl;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@AutoConfigureBefore(JerseyAutoConfiguration.class)
public class EndpointConfig implements Jackson2ObjectMapperBuilderCustomizer {

    @Bean
    public LinkBuilderFactoryImpl linkBuilderFactory() {
        return new LinkBuilderFactoryImpl();
    }

    @Override
    public void customize(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
        jackson2ObjectMapperBuilder.modulesToInstall(new ParameterNamesModule()
        , new Jdk8Module()
        , new JavaTimeModule());
    }

    @Bean
    public JerseyHyperMediaEndPointFactory hyperMediaEndPointFactory(MetaDataProvider metaDataProvider
            , BeanNamingStrategy beanNamingStrategy) {
        return new JerseyHyperMediaEndPointFactory(metaDataProvider, beanNamingStrategy);
    }
}

