package org.divy.common.bo.dynamic.clazz.member.constructor;

import org.divy.common.bo.dynamic.clazz.common.DynamicInitializationValueProvider;

public class DynamicConstructorSuperInitializer extends DynamicConstructorBehaviourContext {

    private DynamicInitializationValueProvider valueProvider;

    DynamicConstructorSuperInitializer(DynamicClassConstructorBuilderContext builderContext
            , Class<?> paramClass
            , DynamicInitializationValueProvider valueProvider) {
        super(builderContext, paramClass);
        this.valueProvider = valueProvider;
    }

    String code() {
        return valueProvider.code();
    }
}
