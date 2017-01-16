package org.divy.common.bo.mapper.dozer.builder;

import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.builder.FieldMapperBuilderContext;
import org.divy.common.bo.mapper.builder.TypeMapperBuilderContext;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.divy.common.bo.mapper.defaults.AdvanceBOMapper;

import java.util.*;

public class DozerTypeMapperBuilderContext<S, T> implements TypeMapperBuilderContext<S, T> {
    private final Class<S> source;
    private final Class<T> target;
    private final List<MapperBuilderOption> mapperBuilderOptions;
    private Map<String, FieldMapperBuilderContext<S, T>> fields;

    public DozerTypeMapperBuilderContext(Class<S> source, Class<T> target, List<MapperBuilderOption> mapperBuilderOptions) {
        this.source = source;
        this.target = target;
        this.mapperBuilderOptions = new ArrayList<>();
        this.fields = new HashMap<>();
    }

    public DozerTypeMapperBuilderContext(Class<S> source, Class<T> target) {
        this(source, target, new ArrayList<>(0));
    }

    public DozerTypeMapperBuilderContext(Class<S> source, Class<T> target, MapperBuilderOption... mapperBuilderOptions) {
        this(source, target, Arrays.asList(mapperBuilderOptions));
    }

    @Override
    public FieldMapperBuilderContext<S, T> field(String fieldName, MapperBuilderOption... mapperBuilderOptions) {
        return this.field(fieldName, fieldName, mapperBuilderOptions);
    }

    @Override
    public FieldMapperBuilderContext<S, T> field(String fieldName) {
        return this.field(fieldName, fieldName);
    }

    @Override
    public FieldMapperBuilderContext<S, T> field(String fieldName, String targetFieldName) {
        return this.field(fieldName, targetFieldName, new MapperBuilderOption[0]);
    }

    @Override
    public FieldMapperBuilderContext<S, T> field(String fieldName, String targetFieldName, MapperBuilderOption... mapperBuilderOptions) {
        FieldMapperBuilderContext<S, T> fieldMapperBuilderContext = new DozerFieldMapperBuilderContext<>(this);
        fieldMapperBuilderContext.targetFieldName(targetFieldName);
        fieldMapperBuilderContext.setFieldMapperBuilderOptions(mapperBuilderOptions);
        this.fields.put(fieldName, fieldMapperBuilderContext);
        return fieldMapperBuilderContext;
    }

    @Override
    public TypeMapperBuilderContext<S, T> autoFieldMapping() {
        return this;
    }

    @Override
    public IBOMapper<S, T> build() {
        return new AdvanceBOMapper<S, T>(source, target, mapperBuilderOptions, fields);
    }
}
