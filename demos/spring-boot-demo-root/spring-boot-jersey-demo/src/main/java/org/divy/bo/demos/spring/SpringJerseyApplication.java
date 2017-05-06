package org.divy.bo.demos.spring;


import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource( value = {"classpath:/application.properties"})
@EnableAutoConfiguration
public class SpringJerseyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SpringJerseyApplication()
                .configure(new SpringApplicationBuilder(SpringJerseyApplication.class))
                .run(args);
    }

    @Bean
    public ResourceConfig resourceConfig() {
        return new ResourceConfig();
    }
}