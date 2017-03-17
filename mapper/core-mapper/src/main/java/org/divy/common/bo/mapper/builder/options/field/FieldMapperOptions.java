package org.divy.common.bo.mapper.builder.options.field;

import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.divy.common.bo.mapper.builder.options.OneWayMappingOption;

public class FieldMapperOptions {

    private FieldMapperOptions(){}
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
    public static MapperBuilderOption converter(Class<?> bClass) {
        return new FieldConverter(bClass);
    }
    public static MapperBuilderOption exclude() {
        return new FieldExclude();
    }

    public static MapperBuilderOption nestedHintB(Class<?> nestedHintClass) {
        return new FieldNestedHintB(nestedHintClass);
    }

    public static MapperBuilderOption nestedHintA(Class<?> nestedHintClass) {
        return new FieldNestedHintA(nestedHintClass);
    }
}
