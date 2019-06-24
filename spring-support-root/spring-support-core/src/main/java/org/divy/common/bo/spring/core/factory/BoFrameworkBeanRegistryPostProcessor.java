package org.divy.common.bo.spring.core.factory;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.util.Collection;

public class BoFrameworkBeanRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    private final MetaDataProvider metaDataProvider;
    private final Collection<DynamicBeansFactory> beansfactories;

    BoFrameworkBeanRegistryPostProcessor(MetaDataProvider metaDataProvider,
                                         Collection<DynamicBeansFactory> beansfactories) {
        this.metaDataProvider = metaDataProvider;
        this.beansfactories = beansfactories;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) {

        beansfactories.forEach(factory -> factory.registerSingletons(beanDefinitionRegistry));
        metaDataProvider.getEntityTypes()
                .forEach(metaData -> registerBeans(metaData, beanDefinitionRegistry));

    }

    private void registerBeans(Class<? extends BusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {
        beansfactories.forEach(beansFactory-> beansFactory.register(type, beanDefinitionRegistry));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) {
        //noop
    }
}
