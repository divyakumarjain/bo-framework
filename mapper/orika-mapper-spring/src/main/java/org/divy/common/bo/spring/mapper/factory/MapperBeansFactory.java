package org.divy.common.bo.spring.mapper.factory;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.mapper.BOMergeMapper;
import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapperImpl;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.core.factory.DynamicBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;


public class MapperBeansFactory implements DynamicBeanFactory<Class<? extends BusinessObject>> {
    
    private final BeanNamingStrategy namingStrategy;

    public MapperBeansFactory(BeanNamingStrategy namingStrategy) {
        this.namingStrategy = namingStrategy;
    }

    @Override
    public void register(Class<? extends BusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {
        beanDefinitionRegistry.registerBeanDefinition(namingStrategy.calculateMergeMapperId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(BOMergeMapper.class)
                        .addConstructorArgValue(type)
                        .addConstructorArgReference("entityMetaDataProvider")
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
    public void registerSingletons(BeanDefinitionRegistry beanDefinitionRegistry)
    {
        //NO OPERATION
    }
}
