package org.divy.bo.demos.spring;


import org.divy.common.bo.database.BoEntityMetaDataProvider;
import org.divy.common.bo.spring.BeanNamingStrategy;
import org.divy.common.bo.spring.BoFrameworkSpringContextInitializer;
import org.divy.common.bo.spring.endpoint.JacksonConfig;
import org.divy.common.bo.spring.endpoint.JerseyEndPointFactory;
import org.divy.common.rest.LinkBuilderFactory;
import org.divy.common.rest.LinkBuilderFactoryImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;

@SpringBootApplication
@PropertySource( value = {"classpath:/application.properties"})
@EnableAutoConfiguration
@Import({JacksonConfig.class})
public class SampleApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        HashMap<String, Object> defaults = new HashMap<>();
        defaults.put("entityTypes", "org.divy.bo.demos.spring.greetings.Greeting");
        new SampleApplication()
                .configure(new SpringApplicationBuilder(SampleApplication.class)
                        .properties(defaults)
                        .initializers(new BoFrameworkSpringContextInitializer()))
                .run(args);
    }

    @Bean
    public LinkBuilderFactory linkBuilderFactory() {
        return new LinkBuilderFactoryImpl();
    }

    @Bean
    public JerseyEndPointFactory endPointFactory(BoEntityMetaDataProvider metaDataProvider
            , BeanNamingStrategy beanNamingStrategy) {
        return new JerseyEndPointFactory(metaDataProvider, beanNamingStrategy);
    }
}