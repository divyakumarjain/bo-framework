package org.divy.bo.demos.spring;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource( value = {"classpath:/application.properties"})
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.divy.common.bo.business.validation.spring.autoconfiguration"})
public class SpringJerseyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SpringJerseyApplication()
                .configure(new SpringApplicationBuilder(SpringJerseyApplication.class))
                .run(args);
    }
}