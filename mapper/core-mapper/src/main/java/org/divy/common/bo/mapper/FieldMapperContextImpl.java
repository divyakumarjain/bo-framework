package org.divy.common.bo.mapper;

import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FieldMapperContextImpl implements FieldMapperContext {
    protected List<MapperBuilderOption> mapperBuilderOptions;

    public FieldMapperContextImpl() {
        this.mapperBuilderOptions = new ArrayList<>();
    }

    @Override
    public List<MapperBuilderOption> getFieldMapperBuilderOptions() {
        return this.mapperBuilderOptions;
    }

    @Override
    public void setFieldMapperBuilderOptions(MapperBuilderOption[] mapperBuilderOptions) {
        this.mapperBuilderOptions = new ArrayList<>();
        this.mapperBuilderOptions.addAll(Arrays.asList(mapperBuilderOptions));
    }
}
