package org.divy.common.bo.mapper.builder.options.field;

import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.divy.common.bo.mapper.builder.options.OneWayMappingOption;

public class FieldMapperOptions {

    public static MapperBuilderOption oneWay() {
        return new OneWayMappingOption();
    }
    public static MapperBuilderOption factory(Class<?> aClass) {
        return new FieldFactoryOption(aClass);
    }
    public static MapperBuilderOption hintA(Class<?> aClass) {
        return new FieldHintA(aClass);
    }

    public static MapperBuilderOption hintB(Class<?> bClass) {
        return new FieldHintB(bClass);
    }
}
