package org.divy.common.bo.mapper.dozer.builder;

import org.divy.common.bo.mapper.builder.FieldMapperBuilderContext;
import org.divy.common.bo.mapper.builder.TypeMapperBuilderContext;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DozerFieldMapperBuilderContext<S, T> implements FieldMapperBuilderContext{
    private TypeMapperBuilderContext typeMapperBuilderContext;
    private List<MapperBuilderOption> mapperBuilderOptions;

    public DozerFieldMapperBuilderContext(TypeMapperBuilderContext typeMapperBuilderContext) {
        this.typeMapperBuilderContext = typeMapperBuilderContext;
        this.mapperBuilderOptions = new ArrayList<>();
    }

    @Override
    public TypeMapperBuilderContext getTypeMapperBuilderContext() {
        return this.typeMapperBuilderContext;
    }

    @Override
    public List<MapperBuilderOption> getMapperBuilderOptions() {
        return this.mapperBuilderOptions;
    }

    @Override
    public void setFieldMapperBuilderOptions(MapperBuilderOption[] mapperBuilderOptions) {
        this.mapperBuilderOptions = new ArrayList<>();
        this.mapperBuilderOptions.addAll(Arrays.asList(mapperBuilderOptions));
    }
}
