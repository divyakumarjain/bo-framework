package org.divy.common.bo.mapper.dozer.builder;

import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.builder.AbstractTypeMapperBuilderContext;
import org.divy.common.bo.mapper.builder.TypeMapperBuilderContext;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.divy.common.bo.mapper.defaults.AdvanceBOMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DozerTypeMapperBuilderContext<S, T> extends AbstractTypeMapperBuilderContext<S, T> {


    public DozerTypeMapperBuilderContext(DozerMapperBuilder builder, Class<S> source, Class<T> target, List<MapperBuilderOption> mapperBuilderOptions) {

        super(builder, source, target);
    }

    public DozerTypeMapperBuilderContext(DozerMapperBuilder builder, Class<S> source, Class<T> target) {
        this(builder, source, target, new ArrayList<>(0));
    }

    public DozerTypeMapperBuilderContext(DozerMapperBuilder builder, Class<S> source, Class<T> target, MapperBuilderOption... mapperBuilderOptions) {
        this(builder, source, target, Arrays.asList(mapperBuilderOptions));
    }

    @Override
    public TypeMapperBuilderContext<S, T> autoFieldMapping() {
        return this;
    }

    @Override
    public IBOMapper<S, T> buildMapper() {
        return new AdvanceBOMapper<>(source, target, mapperBuilderOptions, fields);
    }
}
