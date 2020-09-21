package org.divy.common.bo.dynamic.clazz.member.constructor;

import org.divy.common.bo.dynamic.clazz.DynamicClassBuilderContext;
import org.divy.common.bo.dynamic.clazz.common.DynamicInitializationValueProvider;

class DynamicConstructorFieldInitializer<P extends DynamicClassBuilderContext> extends DynamicConstructorBehaviourContext<P> {

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
