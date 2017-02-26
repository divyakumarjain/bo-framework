package org.divy.mapper.spring;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.mapper.BOMergeMapper;
import org.divy.common.bo.mapper.FieldMapperContext;
import org.divy.common.bo.mapper.FieldMapperContextImpl;
import org.divy.common.bo.mapper.builder.options.field.FieldMapperOptions;
import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapperImpl;
import org.divy.common.bo.spring.DynamicBeanFactory;
import org.divy.common.rest.impl.DefaultHATEOASMapper;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.HashMap;
import java.util.Map;


public class MapperBeansFactoryImpl implements DynamicBeanFactory<Class<? extends IBusinessObject>>, MapperBeansFactory {

    @Override
    public void register(Class<? extends IBusinessObject> type, BeanDefinitionRegistry beanDefinitionRegistry) {
        beanDefinitionRegistry.registerBeanDefinition(calculateMergeMapperId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(BOMergeMapper.class)
                        .addConstructorArgValue(type)
                        .addConstructorArgReference("entityMetaDataProvider")
                        .addConstructorArgReference("mapperBuilder")
                        .getBeanDefinition());

        beanDefinitionRegistry.registerBeanDefinition(calculateHATEOASMapperId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(DefaultHATEOASMapper.class)
                        .addConstructorArgValue(type)
                        .addConstructorArgReference(calculateKeyValueMapper(type))
                        .addConstructorArgReference("linkBuilderFactory")
                        .addConstructorArgReference("entityMetaDataProvider")
                        .getBeanDefinition());

        beanDefinitionRegistry.registerBeanDefinition(calculateKeyValueMapper(type)
                , BeanDefinitionBuilder.genericBeanDefinition(KeyValuePairMapperImpl.class)
                        .addConstructorArgValue(type)
                        .addConstructorArgReference("mapperBuilder")
                        .addConstructorArgReference("entityMetaDataProvider")
                        .getBeanDefinition());
    }

    private String calculateKeyValueMapper(Class<? extends IBusinessObject> type) {
        return calculatePrefix(type) + "Mapper";
    }

    private String calculateHATEOASMapperId(Class<? extends IBusinessObject> type) {
        return calculatePrefix(type) + "HATEOASMapper";
    }

    private Map<String, FieldMapperContext> mergeMappingFieldOptions() {
        Map<String, FieldMapperContext> fields = new HashMap<>(2);

        FieldMapperContext fieldMapperBuilderContext = new FieldMapperContextImpl();
        fieldMapperBuilderContext.setTargetFieldName("lastUpdateTimestamp");
        fieldMapperBuilderContext.addOption(FieldMapperOptions.exclude());
        fields.put("lastUpdateTimestamp", fieldMapperBuilderContext);


        fieldMapperBuilderContext = new FieldMapperContextImpl();
        fieldMapperBuilderContext.setTargetFieldName("createTimestamp");
        fieldMapperBuilderContext.addOption(FieldMapperOptions.exclude());
        fields.put("createTimestamp", fieldMapperBuilderContext);
        return fields;
    }

    @Override
    public String calculateMergeMapperId(Class<? extends IBusinessObject> type) {
        return calculatePrefix(type) + "MergeMapper";
    }
}
