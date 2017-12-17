package org.divy.common.bo.spring.endpoint;


import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.rest.LinkBuilderFactory;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;
import org.divy.common.rest.SpringMVCEntityURLBuilderImpl;
import org.divy.common.rest.SpringMVCLinkBuilderFactoryImpl;
import org.divy.common.rest.response.SpringMVCResponseEntityBuilderFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.hateoas.Link;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
public class WebEndpointConfig implements Jackson2ObjectMapperBuilderCustomizer {

    @Override
    public void customize(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
        jackson2ObjectMapperBuilder.modulesToInstall(new ParameterNamesModule()
        , new Jdk8Module()
        , new JavaTimeModule());
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public LinkBuilderFactory<Link> linkBuilderFactory() {
        return new SpringMVCLinkBuilderFactoryImpl();
    }

    @Bean
    public SpringMVCEntityURLBuilderImpl mvcEntityHyperMediaURLBuilder(@Qualifier("mvcHyperMediaEndPointRegistry") EndPointRegistry springEndPointRegistry) {
        return new SpringMVCEntityURLBuilderImpl(linkBuilderFactory(), springEndPointRegistry);
    }

    @Bean
    public SpringMVCEntityURLBuilderImpl mvcEntityURLBuilder(@Qualifier("mvcEndPointRegistry") EndPointRegistry springHyperMediaEndPointRegistry) {
        return new SpringMVCEntityURLBuilderImpl(linkBuilderFactory(), springHyperMediaEndPointRegistry);
    }

    @Bean
    public ResponseEntityBuilderFactory mvcResponseEntityBuilderHyperMediaFactory(SpringMVCEntityURLBuilderImpl mvcEntityHyperMediaURLBuilder) {
        return new SpringMVCResponseEntityBuilderFactory(mvcEntityHyperMediaURLBuilder);
    }

    @Bean
    public ResponseEntityBuilderFactory mvcResponseEntityBuilderFactory(SpringMVCEntityURLBuilderImpl mvcEntityURLBuilder) {
        return new SpringMVCResponseEntityBuilderFactory(mvcEntityURLBuilder);
    }
}


