package org.divy.common.bo.spring.endpoint;


import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.divy.common.bo.repository.Identifiable;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.rest.LinkBuilderFactory;
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
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Configuration
@AutoConfigureBefore(JerseyAutoConfiguration.class)
public class JerseyEndpointConfig implements Jackson2ObjectMapperBuilderCustomizer {

    @Bean
    public ResourceConfig resourceConfig() {
        final ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(notFoundExceptionMapper());
        resourceConfig.register(badRequestExceptionMapper());
        resourceConfig.register(notAuthorizedExceptionMapper());
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
    public JerseyHyperMediaEndPointClassFactory jerseyHyperMediaEndPointFactory(MetaDataProvider metaDataProvider
          , BeanNamingStrategy beanNamingStrategy
          , EndPointRegistry endPointRegistry
          , JerseyEndpointConfigProperties configProperties) {

        return new JerseyHyperMediaEndPointClassFactory(metaDataProvider
                , beanNamingStrategy
                , endPointRegistry
                , configProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public JerseyEndPointClassFactory jerseyEndPointFactory(MetaDataProvider metaDataProvider
            , BeanNamingStrategy beanNamingStrategy
            ,EndPointRegistry endPointRegistry
            , JerseyEndpointConfigProperties jerseyEndpointConfigProperties) {
        return new JerseyEndPointClassFactory(metaDataProvider
                , beanNamingStrategy
                , endPointRegistry
                , jerseyEndpointConfigProperties);
    }

    @Bean
    public JerseyEntityURLBuilderImpl jerseyEntityURLBuilder(EndPointRegistry endPointRegistry) {
        return new JerseyEntityURLBuilderImpl(linkBuilderFactory(),endPointRegistry);
    }

    @Bean
    @ConditionalOnProperty(prefix = "bo-framework.endpoint.jersey", value = "enable-hateoas-api", havingValue="true")
    public ResponseEntityBuilderFactory<Identifiable<UUID>, Response> jerseyResponseEntityBuilderHyperMediaFactory(EndPointRegistry endPointRegistry) {
        return new JerseyResponseEntityBuilderFactory<>(jerseyEntityURLBuilder(endPointRegistry));
    }

    @Bean
    @ConditionalOnMissingBean
    public ResponseEntityBuilderFactory<Identifiable<UUID>, Response> jerseyResponseEntityBuilderFactory(EndPointRegistry endPointRegistry) {
        return new JerseyResponseEntityBuilderFactory<>(jerseyEntityURLBuilder(endPointRegistry));
    }
}


