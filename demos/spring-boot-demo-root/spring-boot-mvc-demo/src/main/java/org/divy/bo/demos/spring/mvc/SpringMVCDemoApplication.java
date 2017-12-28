package org.divy.bo.demos.spring.mvc;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource( value = {"classpath:/application.properties"})
@ComponentScan(basePackages = {"org.divy.common.bo.validation.spring.autoconfiguration"})
public class SpringMVCDemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SpringMVCDemoApplication()
                .configure(new SpringApplicationBuilder(SpringMVCDemoApplication.class))
                .run(args);
    }
}