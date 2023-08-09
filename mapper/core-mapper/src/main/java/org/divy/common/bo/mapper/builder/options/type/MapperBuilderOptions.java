package org.divy.common.bo.mapper.builder.options.type;

import org.divy.common.bo.mapper.builder.TypeMapperBuilderContext;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.divy.common.bo.mapper.builder.options.OneWayMappingOption;

public class MapperBuilderOptions {
    private MapperBuilderOptions(){}
    public static MapperBuilderOption oneWay() {
        return new OneWayMappingOption();
    }
    public static MapperBuilderOption factory(Factory factory) {
        return new TypeFactoryOption(factory);
    }

    public static MapperBuilderOption factory(Class<? extends Factory> aClass) throws InstantiationException, IllegalAccessException {
        return new TypeFactoryOption(aClass.newInstance());
    }

    public static <S, T> MapperBuilderOption childTypeMapping(TypeMapperBuilderContext<S, T> childMapping) {
        return new ChildTypeMapperOption<>(childMapping);
    }
}
