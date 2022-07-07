package org.divy.common.bo.dynamic.clazz;

public class DynamicClassBuilder {
    private DynamicClassBuilder(){}
    public static DynamicClassBuilderContext createSubClass(Class<?>  aClass) {
        return new DynamicSubClassBuilderContext(aClass);
    }

    public static DynamicClassBuilderContext createClass(String className) {
        return new DynamicClassBuilderContext(className);
    }

}
