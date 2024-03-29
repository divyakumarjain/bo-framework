package org.divy.common.bo.spring.repository.factory;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.business.defaults.DefaultBOManager;
import org.divy.common.bo.database.jpa.defaults.DefaultDBCommandProvider;
import org.divy.common.bo.database.jpa.defaults.DefaultDatabaseRepository;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.core.factory.DynamicBeansFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

public class RepositoryBeansFactory implements DynamicBeansFactory
{

    private BeanNamingStrategy namingStrategy;

    public RepositoryBeansFactory(BeanNamingStrategy namingStrategy) {
        this.namingStrategy = namingStrategy;
    }

    @Override
    public void register(Class<? extends BusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {
        beanDefinitionRegistry.registerBeanDefinition(namingStrategy.calculateRepositoryId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(DefaultDatabaseRepository.class)
                        .addConstructorArgReference(namingStrategy.calculateCommandProviderId(type))
                        .getBeanDefinition());

        beanDefinitionRegistry.registerBeanDefinition(namingStrategy.calculateCommandProviderId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(DefaultDBCommandProvider.class)
                        .addConstructorArgReference("entityManagerCommandContext")
                        .addConstructorArgValue(type)
                        .addConstructorArgReference(namingStrategy.calculateMergeMapperId(type))
                        .getBeanDefinition());



        beanDefinitionRegistry.registerBeanDefinition(namingStrategy.calculateManagerId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(DefaultBOManager.class)
                        .addConstructorArgReference(namingStrategy.calculateRepositoryId(type))
                        .addConstructorArgReference("boValidator")
                        .getBeanDefinition());

    }

    @Override
    public void registerSingletons(BeanDefinitionRegistry beanDefinitionRegistry)
    {
        //NO OPERATION
    }
}
