package org.divy.common.bo.spring.endpoint;


import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.divy.common.bo.Identifiable;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.rest.JerseyEntityURLBuilderImpl;
import org.divy.common.rest.JerseyLinkBuilderFactoryImpl;
import org.divy.common.rest.exception.mapper.BadRequestExceptionMapper;
import org.divy.common.rest.exception.mapper.NotAuthorizedExceptionMapper;
import org.divy.common.rest.exception.mapper.NotFoundExceptionMapper;
import org.divy.common.rest.response.JerseyResponseEntityBuilderFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.ws.rs.core.Response;
import java.util.UUID;

@Configuration
@AutoConfigureBefore(JerseyAutoConfiguration.class)
public class JerseyEndpointConfig implements Jackson2ObjectMapperBuilderCustomizer {

    @Bean
    public ResourceConfig resourceConfig() {
        final ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(notFoundExceptionMapper());
        return resourceConfig;
    }

    @Bean
    public NotFoundExceptionMapper notFoundExceptionMapper() {
        return new NotFoundExceptionMapper();
    }

    @Bean
    public BadRequestExceptionMapper badRequestExceptionMapper() {
        return new BadRequestExceptionMapper();
    }

    @Bean
    public NotAuthorizedExceptionMapper notAuthorizedExceptionMapper() {
        return new NotAuthorizedExceptionMapper();
    }

    @Bean
    public JerseyLinkBuilderFactoryImpl linkBuilderFactory() {
        return new JerseyLinkBuilderFactoryImpl();
    }
    @Override
    public void customize(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
        jackson2ObjectMapperBuilder.modulesToInstall(new ParameterNamesModule()
        , new Jdk8Module()
        , new JavaTimeModule());
    }

    @Bean
    @ConditionalOnProperty(prefix = "bo-framework.endpoint.jersey", value = "enable-hateoas-api", havingValue="true")
    public JerseyHyperMediaEndPointFactory jerseyHyperMediaEndPointFactory(MetaDataProvider metaDataProvider
            , BeanNamingStrategy beanNamingStrategy
            , JerseyEndpointConfigProperties jerseyEndpointConfigProperties) {

        return new JerseyHyperMediaEndPointFactory(metaDataProvider
                , beanNamingStrategy
                , jerseyEntityHyperMediaURLBuilder()
                , jerseyEndpointConfigProperties);
    }

    @Bean
    public JerseyEndPointFactory jerseyEndPointFactory(MetaDataProvider metaDataProvider
            , BeanNamingStrategy beanNamingStrategy
            , JerseyEndpointConfigProperties jerseyEndpointConfigProperties) {
        return new JerseyEndPointFactory(metaDataProvider
                , beanNamingStrategy
        , jerseyEntityURLBuilder(), jerseyEndpointConfigProperties);
    }

    @Bean
    public JerseyEndpointConfigProperties jerseyEndpointConfigProperties() {
        return new JerseyEndpointConfigProperties();
    }

    @Bean
    public JerseyEntityURLBuilderImpl jerseyEntityHyperMediaURLBuilder() {
        return new JerseyEntityURLBuilderImpl(linkBuilderFactory());
    }

    @Bean
    public JerseyEntityURLBuilderImpl jerseyEntityURLBuilder() {
        return new JerseyEntityURLBuilderImpl(linkBuilderFactory());
    }

    @Bean
    public ResponseEntityBuilderFactory<Identifiable<UUID>, Response> jerseyResponseEntityBuilderHyperMediaFactory() {
        return new JerseyResponseEntityBuilderFactory<>(jerseyEntityHyperMediaURLBuilder());
    }

    @Bean
    public ResponseEntityBuilderFactory<Identifiable<UUID>, Response> jerseyResponseEntityBuilderFactory() {
        return new JerseyResponseEntityBuilderFactory<>(jerseyEntityURLBuilder());
    }
}


