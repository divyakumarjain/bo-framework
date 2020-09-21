package org.divy.common.bo.dynamic.clazz;

public class DynamicClassBuilder {
    private DynamicClassBuilder(){}
    public static DynamicClassBuilderContext<DynamicSubClassBuilderContext> createSubClass(Class<?>  aClass) {
        return new DynamicSubClassBuilderContext(aClass);
    }

    public static DynamicClassBuilderContext<DynamicClassBuilderContext> createClass(String className) {
        return new DynamicClassBuilderContext<>(className);
    }

}
