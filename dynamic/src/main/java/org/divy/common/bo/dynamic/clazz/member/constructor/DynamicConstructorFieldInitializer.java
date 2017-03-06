package org.divy.common.bo.dynamic.clazz.member.constructor;

import org.divy.common.bo.dynamic.clazz.common.DynamicInitializationValueProvider;

class DynamicConstructorFieldInitializer extends DynamicConstructorBehaviourContext {

    private String fieldName;
    private DynamicInitializationValueProvider valueProvider;

    DynamicConstructorFieldInitializer(DynamicClassConstructorBuilderContext builderContext
            , String fieldName
            , Class<?> paramClass
            , DynamicInitializationValueProvider valueProvider) {
        super(builderContext, paramClass);
        this.fieldName = fieldName;
        this.valueProvider = valueProvider;
    }

    String code() {
        return "this."+fieldName +  " = " + valueProvider.code() + ";";
    }
}
