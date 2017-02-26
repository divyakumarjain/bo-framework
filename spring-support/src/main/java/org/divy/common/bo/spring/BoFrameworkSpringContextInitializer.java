package org.divy.common.bo.spring;

import org.divy.common.bo.database.BoEntityMetaDataProvider;
import org.divy.common.bo.spring.context.BoFrameworkSpringContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.GenericApplicationContext;

public class BoFrameworkSpringContextInitializer implements ApplicationContextInitializer<GenericApplicationContext>, ApplicationListener<ApplicationEvent> {

    private ConfigurableApplicationContext context;


    public BoFrameworkSpringContextInitializer() {
    }

    @Override
    public void initialize(GenericApplicationContext applicationContext) {
        context = new AnnotationConfigApplicationContext(BoFrameworkSpringContext.class);
        applicationContext.setParent(context);
        Object bean;
        bean = context.getBean("entityMetaDataProvider");
        final BoEntityMetaDataProvider entityMetaDataProvider = (BoEntityMetaDataProvider) bean;
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
