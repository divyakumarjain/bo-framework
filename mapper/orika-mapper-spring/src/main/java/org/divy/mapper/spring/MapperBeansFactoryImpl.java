package org.divy.mapper.spring;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.mapper.BOMergeMapper;
import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapperImpl;
import org.divy.common.bo.spring.BeanNamingStrategy;
import org.divy.common.bo.spring.DynamicBeanFactory;
import org.divy.common.rest.impl.DefaultHATEOASMapper;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;


public class MapperBeansFactoryImpl implements DynamicBeanFactory<Class<? extends IBusinessObject>> {
    
    private final BeanNamingStrategy namingStrategy;

    public MapperBeansFactoryImpl(BeanNamingStrategy namingStrategy) {
        this.namingStrategy = namingStrategy;
    }

    @Override
    public void register(Class<? extends IBusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {
        beanDefinitionRegistry.registerBeanDefinition(namingStrategy.calculateMergeMapperId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(BOMergeMapper.class)
                        .addConstructorArgValue(type)
                        .addConstructorArgReference("entityMetaDataProvider")
                        .addConstructorArgReference("mapperBuilder")
                        .getBeanDefinition());

        beanDefinitionRegistry.registerBeanDefinition(namingStrategy.calculateHATEOASMapperId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(DefaultHATEOASMapper.class)
                        .addConstructorArgValue(type)
                        .addConstructorArgReference(namingStrategy.calculateKeyValueMapper(type))
                        .addConstructorArgReference("linkBuilderFactory")
                        .addConstructorArgReference("entityMetaDataProvider")
                        .getBeanDefinition());

        beanDefinitionRegistry.registerBeanDefinition(namingStrategy.calculateKeyValueMapper(type)
                , BeanDefinitionBuilder.genericBeanDefinition(KeyValuePairMapperImpl.class)
                        .addConstructorArgValue(type)
                        .addConstructorArgReference("mapperBuilder")
                        .addConstructorArgReference("entityMetaDataProvider")
                        .getBeanDefinition());
    }
}
