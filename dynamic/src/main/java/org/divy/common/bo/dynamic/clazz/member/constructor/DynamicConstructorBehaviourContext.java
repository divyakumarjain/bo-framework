package org.divy.common.bo.dynamic.clazz.member.constructor;

import javassist.CtClass;
import org.divy.common.bo.dynamic.clazz.common.DynamicAnnotatableBuilderContext;

import java.util.Optional;

class DynamicConstructorBehaviourContext
        extends DynamicAnnotatableBuilderContext<DynamicConstructorBehaviourContext, DynamicClassConstructorBuilderContext> {

    private final Class<?> paramClass;

    DynamicConstructorBehaviourContext(DynamicClassConstructorBuilderContext builderContext, Class<?> paramClass) {
        super(builderContext);
        this.paramClass = paramClass;
    }

    Optional<CtClass> getParamCtClass() {
        return getCtClass(paramClass);
    }
}
