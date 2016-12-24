package org.divy.common.bo.spring;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.spring.context.BoFrameworkSpringContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class BoFrameworkSpringContextInitializer implements ApplicationContextInitializer<GenericApplicationContext>, ApplicationListener<ApplicationEvent> {

    private ConfigurableApplicationContext context;


    public BoFrameworkSpringContextInitializer() {
    }

    @Override
    public void initialize(GenericApplicationContext applicationContext) {
        context = new AnnotationConfigApplicationContext(BoFrameworkSpringContext.class);
        applicationContext.setParent(context);
        applicationContext.addBeanFactoryPostProcessor(new BoFrameworkBeanRegistryPostProcessor(
                (List<Class<? extends IBusinessObject>>) context.getBean("typeList"),
                context.getBean("dynamicBeanFactory", DynamicBeanFactory.class)));
        applicationContext.addApplicationListener(this);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ContextClosedEvent) {
            context.stop();
        }
    }
}
