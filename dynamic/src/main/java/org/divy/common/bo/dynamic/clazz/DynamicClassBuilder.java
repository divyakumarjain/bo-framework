package org.divy.common.bo.dynamic.clazz;

public class DynamicClassBuilder {
    private DynamicClassBuilder(){}
    public static DynamicClassBuilderContext createSubClass(Class<?>  aClass) {
        return new DynamicSubClassBuilderContext(aClass);
    }

    public static <C extends DynamicClassBuilderContext> DynamicClassBuilderContext createClass(String className) {
        return new DynamicClassBuilderContext(className);
    }

}
