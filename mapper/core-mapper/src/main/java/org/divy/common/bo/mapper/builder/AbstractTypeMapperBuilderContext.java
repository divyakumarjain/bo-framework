package org.divy.common.bo.mapper.builder;

import org.divy.common.bo.mapper.FieldMapperContext;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.divy.common.bo.mapper.builder.options.type.MapperBuilderOptions;

import java.util.*;

public abstract class AbstractTypeMapperBuilderContext<S, T> implements TypeMapperBuilderContext<S, T> {
    private final MapperBuilder builder;
    protected final Class<S> source;
    protected final Class<T> target;
    protected final List<MapperBuilderOption> mapperBuilderOptions = new ArrayList<>();
    protected final Map<String, FieldMapperContext> fields;

    public AbstractTypeMapperBuilderContext(MapperBuilder builder, Class<S> source, Class<T> target) {
        this.builder = builder;
        this.source = source;
        this.target = target;
        this.fields = new HashMap<>();
    }

    @Override
    public TypeMapperBuilderContext<S, T> addTypeMappingOption(MapperBuilderOption... mapperBuilderOptions) {
        this.mapperBuilderOptions.addAll(Arrays.asList(mapperBuilderOptions));
        return this;
    }

    @Override
    public FieldMapperBuilderContext<S, T> field(String fieldName, MapperBuilderOption... mapperBuilderOptions) {
        return this.field(fieldName, fieldName, mapperBuilderOptions);
    }

    @Override
    public FieldMapperBuilderContext<S, T> field(String fieldName, String targetFieldName, List<MapperBuilderOption> mapperBuilderOptions) {
        return field(fieldName, targetFieldName, mapperBuilderOptions.toArray(new MapperBuilderOption[mapperBuilderOptions.size()]));
    }

    @Override
    public FieldMapperBuilderContext<S, T> field(String fieldName, List<MapperBuilderOption> mapperBuilderOptions) {
        return field(fieldName, fieldName, mapperBuilderOptions.toArray(new MapperBuilderOption[mapperBuilderOptions.size()]));
    }

    @Override
    public FieldMapperBuilderContext<S, T> field(String fieldName) {
        return field(fieldName, fieldName);
    }

    @Override
    public FieldMapperBuilderContext<S, T> field(String fieldName, String targetFieldName) {
        return field(fieldName, targetFieldName, new MapperBuilderOption[0]);
    }

    @Override
    public FieldMapperBuilderContext<S, T> field(String fieldName, String targetFieldName, MapperBuilderOption... mapperBuilderOptions) {
        FieldMapperBuilderContext<S, T> fieldMapperBuilderContext = new FieldMapperBuilderContextImpl<>(this);
        fieldMapperBuilderContext.targetFieldName(targetFieldName);
        fieldMapperBuilderContext.setFieldMapperBuilderOptions(mapperBuilderOptions);
        this.fields.put(fieldName, fieldMapperBuilderContext);
        return fieldMapperBuilderContext;
    }

    @Override
    public <CS, CT> TypeMapperBuilderContext<CS, CT> childTypeMapperBuilder(Class<CS> source, Class<CT> target, MapperBuilderOption... mapperBuilderOptions) {
        final TypeMapperBuilderContext<CS, CT> childMapping = builder.mapping(source, target, mapperBuilderOptions);
        this.addTypeMappingOption(MapperBuilderOptions.childTypeMapping(childMapping));
        return childMapping;
    }

    @Override
    public TypeMapperBuilderContext<S, T> autoFieldMapping() {
        return this;
    }
}
