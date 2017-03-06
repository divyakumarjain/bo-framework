package org.divy.common.bo.spring;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.defaults.DefaultBOManager;
import org.divy.common.bo.database.defaults.DefaultDBCommandProvider;
import org.divy.common.bo.database.defaults.DefaultDatabaseRepository;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

public class BoBeansFactory implements DynamicBeanFactory<Class<? extends IBusinessObject>> {

    private BeanNamingStrategy namingStrategy;

    public BoBeansFactory(BeanNamingStrategy namingStrategy) {
        this.namingStrategy = namingStrategy;
    }

    @Override
    public void register(Class<? extends IBusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {
        beanDefinitionRegistry.registerBeanDefinition(namingStrategy.calculateRepositoryId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(DefaultDatabaseRepository.class)
                        .addConstructorArgReference(namingStrategy.calculateCommandProviderId(type))
                        .getBeanDefinition());

        beanDefinitionRegistry.registerBeanDefinition(namingStrategy.calculateCommandProviderId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(DefaultDBCommandProvider.class)
                        .addConstructorArgValue("${persistentUnitName}")
                        .addConstructorArgValue(type)
                        .addConstructorArgReference(namingStrategy.calculateMergeMapperId(type))
                        .getBeanDefinition());



        beanDefinitionRegistry.registerBeanDefinition(namingStrategy.calculateManagerId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(DefaultBOManager.class)
                        .addConstructorArgReference(namingStrategy.calculateRepositoryId(type))
                        .getBeanDefinition());

    }
}
