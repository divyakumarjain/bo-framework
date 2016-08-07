package org.divy.bo.demos.spring;


import org.divy.common.rest.LinkBuilderFactory;
import org.divy.common.rest.LinkBuilderFactoryImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SampleJerseyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SampleJerseyApplication()
                .configure(new SpringApplicationBuilder(SampleJerseyApplication.class))
                .run(args);
    }

    @Bean
    public LinkBuilderFactory linkBuilder() {
        return new LinkBuilderFactoryImpl();
    }

}