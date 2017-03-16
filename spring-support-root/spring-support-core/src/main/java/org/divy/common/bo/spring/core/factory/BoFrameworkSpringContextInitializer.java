package org.divy.common.bo.spring.core.factory;

import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.spring.core.factory.context.BoFrameworkSpringContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.GenericApplicationContext;

public class BoFrameworkSpringContextInitializer implements ApplicationContextInitializer<GenericApplicationContext>, ApplicationListener<ApplicationEvent> {

    private ConfigurableApplicationContext context;


    @Override
    public void initialize(GenericApplicationContext applicationContext) {
        context = new AnnotationConfigApplicationContext(BoFrameworkSpringContext.class);
        applicationContext.setParent(context);
        Object bean;
        bean = context.getBean("entityMetaDataProvider");
        final MetaDataProvider entityMetaDataProvider = (MetaDataProvider) bean;
        applicationContext.addBeanFactoryPostProcessor(new BoFrameworkBeanRegistryPostProcessor(
                entityMetaDataProvider,
                context.getBeansOfType(DynamicBeanFactory.class).values()));
        applicationContext.addApplicationListener(this);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ContextClosedEvent) {
            context.stop();
        }
    }
}