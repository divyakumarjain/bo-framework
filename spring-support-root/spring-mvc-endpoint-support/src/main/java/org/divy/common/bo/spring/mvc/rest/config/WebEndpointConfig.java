package org.divy.common.bo.spring.mvc.rest.config;


import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.divy.common.bo.rest.LinkBuilderFactory;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;
import org.divy.common.bo.spring.mvc.rest.SpringMVCEndPointRegistry;
import org.divy.common.bo.spring.mvc.rest.SpringMVCEntityURLBuilderImpl;
import org.divy.common.bo.spring.mvc.rest.SpringMVCLinkBuilderFactoryImpl;
import org.divy.common.bo.spring.mvc.rest.response.SpringMVCResponseEntityBuilderFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
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
        jackson2ObjectMapperBuilder.modulesToInstall(ParameterNamesModule.class
        ,  Jdk8Module.class
        , JavaTimeModule.class);
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public LinkBuilderFactory<Link> linkBuilderFactory() {
        return new SpringMVCLinkBuilderFactoryImpl();
    }

    @Bean
    @ConditionalOnProperty(value = "bo-framework.endpoint.mvc.enable-hateoas-api", havingValue="true")
    public SpringMVCEntityURLBuilderImpl mvcEntityHATOASURLBuilder( SpringMVCEndPointRegistry mvcEndPointRegistry) {
        return new SpringMVCEntityURLBuilderImpl(linkBuilderFactory(), mvcEndPointRegistry);
    }

    @Bean
    public SpringMVCEntityURLBuilderImpl mvcEntityURLBuilder(SpringMVCEndPointRegistry mvcEndPointRegistry) {
        return new SpringMVCEntityURLBuilderImpl(linkBuilderFactory(), mvcEndPointRegistry);
    }

    @Bean
    public ResponseEntityBuilderFactory mvcResponseEntityBuilderHATOASFactory(SpringMVCEntityURLBuilderImpl mvcEntityHATOASURLBuilder) {
        return new SpringMVCResponseEntityBuilderFactory(mvcEntityHATOASURLBuilder);
    }

    @Bean
    public ResponseEntityBuilderFactory mvcResponseEntityBuilderFactory(SpringMVCEntityURLBuilderImpl mvcEntityURLBuilder) {
        return new SpringMVCResponseEntityBuilderFactory(mvcEntityURLBuilder);
    }
}


