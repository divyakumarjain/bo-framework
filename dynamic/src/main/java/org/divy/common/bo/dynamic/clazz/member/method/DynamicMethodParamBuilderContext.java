package org.divy.common.bo.dynamic.clazz.member.method;

import javassist.CtBehavior;
import javassist.CtClass;
import org.divy.common.bo.dynamic.clazz.common.DynamicAnnotatableBuilderContext;
import org.divy.common.bo.dynamic.clazz.common.DynamicInitializationValueProvider;

public class DynamicMethodParamBuilderContext<P extends DynamicMethodBuilderContext>
        extends DynamicAnnotatableBuilderContext<DynamicMethodParamBuilderContext<P>, P>
    implements DynamicInitializationValueProvider {
    private int index = 1;
    private CtClass paramClass;

    DynamicMethodParamBuilderContext(P parentContext, CtClass paramClass, int paramIndex) {
        super(parentContext);
        this.index(paramIndex);
        this.paramClass = paramClass;
    }

    DynamicMethodParamBuilderContext(P parentContext) {
        super(parentContext);
    }

    DynamicMethodParamBuilderContext index(int i) {
        this.index = i;
        return this;
    }

    public DynamicMethodParamBuilderContext<P> type(Class<?> paramClass) {
        getCtClass(paramClass).ifPresent(paramCtClass-> this.paramClass = paramCtClass);
        return this;
    }


    CtClass getParamCtClass() {
        return paramClass;
    }

    @Override
    public String code() {
        return "$"+(index+1);
    }

    public void doBuild(CtBehavior behavior) {
        this.annotations.forEach(annotation-> annotation.doBuild(behavior, index));
    }

    public P and() {
        return getParentContext();
    }
}
