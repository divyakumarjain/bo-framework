package org.divy.common.bo.spring;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.defaults.DefaultBOManager;
import org.divy.common.bo.database.defaults.DefaultDBCommandProvider;
import org.divy.common.bo.database.defaults.DefaultDatabaseRepository;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

public class BoBeansFactory implements DynamicBeanFactory<Class<? extends IBusinessObject>> {

    @Override
    public void register(Class<? extends IBusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {
        beanDefinitionRegistry.registerBeanDefinition(calculateRepositoryId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(DefaultDatabaseRepository.class)
                        .addConstructorArgReference(calculateCommandProviderId(type))
                        .getBeanDefinition());

        beanDefinitionRegistry.registerBeanDefinition(calculateCommandProviderId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(DefaultDBCommandProvider.class)
                        .addConstructorArgValue("${persistentUnitName}")
                        .addConstructorArgValue(type)
                        .addConstructorArgReference(calculateMergeMapperId(type))
                        .getBeanDefinition());



        beanDefinitionRegistry.registerBeanDefinition(calculateManagerId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(DefaultBOManager.class)
                        .addConstructorArgReference(calculateRepositoryId(type))
                        .getBeanDefinition());

    }


    private String calculateManagerId(Class<? extends IBusinessObject> type) {
        return calculatePrefix(type) + "Manager";
    }

    private String calculateCommandProviderId(Class<? extends IBusinessObject> type) {
        return calculatePrefix(type) + "CommandProvider";
    }

    private String calculateRepositoryId(Class<? extends IBusinessObject> type) {
        return calculatePrefix(type) + "Repository";
    }

    public String calculateMergeMapperId(Class<? extends IBusinessObject> type) {
        return calculatePrefix(type) + "MergeMapper";
    }
}
