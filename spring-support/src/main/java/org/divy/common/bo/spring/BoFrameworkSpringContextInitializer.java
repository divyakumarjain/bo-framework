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
        Object bean = null;
        try {
            bean = context.getBean("entityMetaDataProvider");
            final BoEntityMetaDataProvider entityMetaDataProvider = (BoEntityMetaDataProvider) bean;
            applicationContext.addBeanFactoryPostProcessor(new BoFrameworkBeanRegistryPostProcessor(
                    entityMetaDataProvider,
                    context.getBean("dynamicBeanFactory", DynamicBeanFactory.class)));
            applicationContext.addApplicationListener(this);
        } catch(ClassCastException exception) {
            System.out.println("Could not load entityMetaDataProvider");
            System.out.println(BoEntityMetaDataProvider.class.getClassLoader().getResource("org/divy/common/bo/database/BoEntityMetaDataProvider.class"));
            System.out.println(BoEntityMetaDataProvider.class.getClassLoader().toString());
            if(bean != null)
                System.out.println(bean.getClass().getClassLoader().getResource("org/divy/common/bo/database/BoEntityMetaDataProvider.class"));
                System.out.println(bean.getClass().getClassLoader().toString());

        }
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ContextClosedEvent) {
            context.stop();
        }
    }
}
