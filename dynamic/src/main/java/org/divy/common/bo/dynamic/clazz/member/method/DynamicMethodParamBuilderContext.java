package org.divy.common.bo.dynamic.clazz.member.method;

import javassist.CtBehavior;
import javassist.CtClass;
import org.divy.common.bo.dynamic.clazz.common.DynamicAnnotatableBuilderContext;
import org.divy.common.bo.dynamic.clazz.common.DynamicInitializationValueProvider;

import java.util.Optional;

public class DynamicMethodParamBuilderContext<P extends DynamicMethodBuilderContext>
        extends DynamicAnnotatableBuilderContext<DynamicMethodParamBuilderContext<P>, P>
    implements DynamicInitializationValueProvider {
    private int index = 1;
    private Class<?> paramClass;

    DynamicMethodParamBuilderContext(P parentContext, Class<?> paramClass) {
        super(parentContext);
        this.paramClass = paramClass;
    }

    DynamicMethodParamBuilderContext index(int i) {
        this.index = i;
        return this;
    }


    Optional<CtClass> getParamCtClass() {
        return getCtClass(paramClass);
    }

    @Override
    public String code() {
        return "$"+index;
    }

    public void doBuild(CtBehavior behavior) {
        this.annotations.forEach(annotation-> annotation.doBuild(behavior, index));
    }

}
