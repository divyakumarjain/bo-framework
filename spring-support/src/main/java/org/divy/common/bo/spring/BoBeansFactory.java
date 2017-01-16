package org.divy.common.bo.spring;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.defaults.DefaultBOManager;
import org.divy.common.bo.database.defaults.DefaultDBCommandProvider;
import org.divy.common.bo.database.defaults.DefaultDatabaseRepository;
import org.divy.common.rest.impl.DefaultHATEOASMapper;
import org.divy.common.rest.impl.KeyValuePairMapperImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;

public class BoBeansFactory implements DynamicBeanFactory<Class<? extends IBusinessObject>> {
    @Override
    public void register(Class<? extends IBusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {
        beanDefinitionRegistry.registerBeanDefinition(getCamelCaseName(type) + "Repository"
                , BeanDefinitionBuilder.genericBeanDefinition(DefaultDatabaseRepository.class)
                        .addConstructorArgReference(getCamelCaseName(type) + "CommandProvider")
                        .getBeanDefinition());

        beanDefinitionRegistry.registerBeanDefinition(getCamelCaseName(type) + "CommandProvider"
                , BeanDefinitionBuilder.genericBeanDefinition(DefaultDBCommandProvider.class)
                        .addConstructorArgValue("${persistentUnitName}")
                        .addConstructorArgValue(type)
                        .addConstructorArgReference("mapperBuilder")
                        .getBeanDefinition());

        beanDefinitionRegistry.registerBeanDefinition(getCamelCaseName(type) + "Manager"
                , BeanDefinitionBuilder.genericBeanDefinition(DefaultBOManager.class)
                        .addConstructorArgReference(getCamelCaseName(type) + "Repository")
                        .getBeanDefinition());

        beanDefinitionRegistry.registerBeanDefinition( getCamelCaseName(type) + "HATEOASMapper"
                , BeanDefinitionBuilder.genericBeanDefinition(DefaultHATEOASMapper.class)
                        .addConstructorArgValue(type)
                        .addConstructorArgReference(getCamelCaseName(type) + "Mapper")
                        .addConstructorArgReference("linkBuilderFactory")
                        .addConstructorArgReference("entityMetaDataProvider")
                        .getBeanDefinition());

        beanDefinitionRegistry.registerBeanDefinition(getCamelCaseName(type) + "Mapper"
                , BeanDefinitionBuilder.genericBeanDefinition(KeyValuePairMapperImpl.class)
                        .addConstructorArgValue(type)
                        .addConstructorArgReference("mapperBuilder")
                        .addConstructorArgReference("entityMetaDataProvider")
                        .getBeanDefinition());

    }

    private String getCamelCaseName(Class<? extends IBusinessObject> type) {
        return Introspector.decapitalize(ClassUtils.getShortName(type));
    }
}
