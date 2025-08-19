package org.divy.common.bo.spring.mapper.factory;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.mapper.modelmapper.BOMergeModelMapper;
import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapperImpl;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.core.factory.DynamicBeansFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

public class ModelMapperBeansFactory implements DynamicBeansFactory {
    
    private final BeanNamingStrategy namingStrategy;

    public ModelMapperBeansFactory(BeanNamingStrategy namingStrategy) {
        this.namingStrategy = namingStrategy;
    }

    @Override
    public void register(Class<? extends BusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {
        beanDefinitionRegistry.registerBeanDefinition(namingStrategy.calculateMergeMapperId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(BOMergeModelMapper.class)
                        .addConstructorArgValue(type)
                        .addConstructorArgReference("mapperBuilder")
                        .getBeanDefinition());

        beanDefinitionRegistry.registerBeanDefinition(namingStrategy.calculateKeyValueMapper(type)
                , BeanDefinitionBuilder.genericBeanDefinition(KeyValuePairMapperImpl.class)
                        .addConstructorArgValue(type)
                        .addConstructorArgReference("mapperBuilder")
                        .addConstructorArgReference("entityMetaDataProvider")
                        .getBeanDefinition());
    }

    @Override
    public void registerSingletons(BeanDefinitionRegistry beanDefinitionRegistry) {
        //NO OPERATION
    }
}
