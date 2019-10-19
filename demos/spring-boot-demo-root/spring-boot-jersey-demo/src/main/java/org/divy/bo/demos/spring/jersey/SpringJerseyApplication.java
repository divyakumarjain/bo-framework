package org.divy.bo.demos.spring.jersey;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource( value = {"classpath:/application.properties"})
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.divy.common.bo.validation.spring.autoconfiguration"})
public class SpringJerseyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SpringJerseyApplication()
                .configure(new SpringApplicationBuilder(SpringJerseyApplication.class))
                .run(args);
    }
}