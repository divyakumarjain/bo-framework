package org.divy.common.bo.spring.context;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.spring.BoBeansFactory;
import org.divy.common.bo.spring.DynamicBeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@PropertySource( value = {"classpath:/application.properties"})
public class BoFrameworkSpringContext {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DynamicBeanFactory<Class<? extends IBusinessObject>> dynamicBeanFactory() {
        return new BoBeansFactory();
    }

    @Bean
    List<Class<? extends IBusinessObject>> typeList(@Value("${entityTypes}") String typesList) {
        return Arrays.stream(typesList.split(","))
                .map(className -> {
                    try {
                        return (Class<? extends IBusinessObject>)this.getClass().forName(className);
                    } catch (ClassNotFoundException e) {
                        return null;
                    }
                })
                .filter(clazz -> clazz!=null)
                .collect(Collectors.toList());
    }
}
