package org.divy.common.bo.spring;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.database.BoEntityMetaDataProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.util.Collection;

public class BoFrameworkBeanRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    private final BoEntityMetaDataProvider metaDataProvider;
    private final Collection<DynamicBeanFactory> factories;

    BoFrameworkBeanRegistryPostProcessor(BoEntityMetaDataProvider metaDataProvider,
                                         Collection<DynamicBeanFactory> factories) {
        this.metaDataProvider = metaDataProvider;
        this.factories = factories;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) {

        metaDataProvider.getEntityTypes().parallelStream()
                .forEach(metaData -> registerBeans(metaData, beanDefinitionRegistry));

    }

    private void registerBeans(Class<? extends IBusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {
        factories.forEach(factory-> factory.register(type, beanDefinitionRegistry));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) {
        //noop
    }
}
