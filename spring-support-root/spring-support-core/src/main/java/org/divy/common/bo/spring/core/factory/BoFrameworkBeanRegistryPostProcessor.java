package org.divy.common.bo.spring.core.factory;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.util.Collection;

public class BoFrameworkBeanRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    private final MetaDataProvider metaDataProvider;
    private final Collection<DynamicBeanFactory> factories;

    BoFrameworkBeanRegistryPostProcessor(MetaDataProvider metaDataProvider,
                                         Collection<DynamicBeanFactory> factories) {
        this.metaDataProvider = metaDataProvider;
        this.factories = factories;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) {

        metaDataProvider.getEntityTypes()
                .forEach(metaData -> registerBeans(metaData, beanDefinitionRegistry));

    }

    private void registerBeans(Class<? extends BusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {
        factories.forEach(factory-> factory.register(type, beanDefinitionRegistry));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) {
        //noop
    }
}
