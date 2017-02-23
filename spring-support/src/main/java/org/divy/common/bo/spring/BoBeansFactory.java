package org.divy.common.bo.spring;

import ma.glasnost.orika.converter.builtin.DateAndTimeConverters;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.defaults.DefaultBOManager;
import org.divy.common.bo.database.defaults.DefaultDBCommandProvider;
import org.divy.common.bo.database.defaults.DefaultDatabaseRepository;
import org.divy.common.bo.mapper.FieldMapperContext;
import org.divy.common.bo.mapper.FieldMapperContextImpl;
import org.divy.common.bo.mapper.builder.options.field.FieldMapperOptions;
import org.divy.common.bo.mapper.defaults.AdvanceBOMapper;
import org.divy.common.rest.impl.DefaultHATEOASMapper;
import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapperImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

        beanDefinitionRegistry.registerBeanDefinition(calculateMergeMapperId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(AdvanceBOMapper.class)
                        .addConstructorArgValue(type)
                        .addConstructorArgValue(type)
                        .addConstructorArgValue(Collections.emptyList())
                        .addConstructorArgValue(mergeMappingFieldOptions())
                        .getBeanDefinition());

        beanDefinitionRegistry.registerBeanDefinition(calculateManagerId(type)
                , BeanDefinitionBuilder.genericBeanDefinition(DefaultBOManager.class)
                        .addConstructorArgReference(calculateRepositoryId(type))
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

    private String calculateKeyValueMapper(Class<? extends IBusinessObject> type) {
        return getCamelCaseName(type) + "Mapper";
    }

    private String calculateHATEOASMapperId(Class<? extends IBusinessObject> type) {
        return getCamelCaseName(type) + "HATEOASMapper";
    }

    private String calculateManagerId(Class<? extends IBusinessObject> type) {
        return getCamelCaseName(type) + "Manager";
    }

    private String calculateMergeMapperId(Class<? extends IBusinessObject> type) {
        return getCamelCaseName(type) + "MergeMapper";
    }

    private String calculateCommandProviderId(Class<? extends IBusinessObject> type) {
        return getCamelCaseName(type) + "CommandProvider";
    }

    private String calculateRepositoryId(Class<? extends IBusinessObject> type) {
        return getCamelCaseName(type) + "Repository";
    }

    private String getCamelCaseName(Class<? extends IBusinessObject> type) {
        return Introspector.decapitalize(ClassUtils.getShortName(type));
    }
}
