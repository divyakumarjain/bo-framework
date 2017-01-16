package org.divy.common.bo.spring;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.database.BoEntityMetaDataProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

public class BoFrameworkBeanRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    private final BoEntityMetaDataProvider metaDataProvider;
    private DynamicBeanFactory<Class<? extends IBusinessObject>> factory;

    BoFrameworkBeanRegistryPostProcessor(BoEntityMetaDataProvider metaDataProvider,
                                         DynamicBeanFactory<Class<? extends IBusinessObject>> factory) {
        this.metaDataProvider = metaDataProvider;
        this.factory = factory;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) {

        metaDataProvider.getEntityTypes().parallelStream()
                .forEach(metaData -> registerBeans(metaData, beanDefinitionRegistry));

    }

    private void registerBeans(Class<? extends IBusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {
        factory.register(type, beanDefinitionRegistry);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) {
        //noop
    }
}
