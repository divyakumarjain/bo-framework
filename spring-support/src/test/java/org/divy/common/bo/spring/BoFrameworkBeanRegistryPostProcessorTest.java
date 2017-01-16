package org.divy.common.bo.spring;

import org.divy.common.bo.IBORepository;
import org.divy.common.rest.LinkBuilderFactory;
import org.divy.common.rest.LinkBuilderFactoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(initializers = {BoFrameworkSpringContextInitializer.class}, classes = BoFrameworkBeanRegistryPostProcessorTest.class)
@PropertySource( value = {"classpath:/application.properties"})
public class BoFrameworkBeanRegistryPostProcessorTest implements ApplicationContextAware{

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Test
    public void testRepository() {
        IBORepository repository = applicationContext.getBean("mockEntityRepository", IBORepository.class);
        assertThat(repository, is(notNullValue(IBORepository.class)));
    }

    @Bean
    public HttpServletRequest httpServletRequest() {
        return mock(HttpServletRequest.class);
    }

    @Bean
    public LinkBuilderFactory linkBuilderFactory() {
        return new LinkBuilderFactoryImpl();
    }
}