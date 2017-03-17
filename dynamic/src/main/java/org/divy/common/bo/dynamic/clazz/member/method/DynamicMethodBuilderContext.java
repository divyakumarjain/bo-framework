package org.divy.common.bo.dynamic.clazz.member.method;

import javassist.*;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilderContext;
import org.divy.common.bo.dynamic.clazz.member.DynamicMemberBuilderContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DynamicMethodBuilderContext<T extends DynamicMethodBuilderContext> extends DynamicMemberBuilderContext<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(org.divy.common.bo.dynamic.clazz.member.constructor.DynamicClassConstructorBuilderContext.class);
    protected List<DynamicMethodParamBuilderContext> parameters = new ArrayList<>();
    private String body="";
    private int paramCount=0;

    public DynamicMethodBuilderContext(DynamicClassBuilderContext builderContext) {
        super(builderContext);
    }

    protected DynamicMethodParamBuilderContext<T> param(Class<?> paramClass) {
        final DynamicMethodParamBuilderContext<T> paramBuilderContext =
                new DynamicMethodParamBuilderContext<>((T)this, paramClass);

        paramBuilderContext.index(++paramCount);
        parameters.add(paramBuilderContext);
        return paramBuilderContext;
    }

    public DynamicMethodBuilderContext body(String body) {
        this.body = body;
        return this;
    }

    public void doBuild(CtClass newClass) {

            getMemberType().ifPresent(type -> {
                try {
                    CtMethod method = CtNewMethod.make( type
                            , getMemberName()
                            , getParameters()
                            , getExceptions()
                            , getBody()
                            , newClass);
                    newClass.addMethod(method);
                    doBuild(method);
                } catch (CannotCompileException e) {
                    LOGGER.error("Could not add method to the class", e);
                }
            });
    }

    protected void doBuild(CtBehavior behavior) {
        annotations.forEach(annotation -> annotation.doBuild(behavior));
        parameters.stream()
                .forEachOrdered(param -> param.doBuild(behavior));
    }

    private String getBody() {
        return body;
    }

    protected CtClass[] getExceptions() {
        return new CtClass[0];
    }

    protected CtClass[] getParameters() {

        return parameters.stream()
                .map(DynamicMethodParamBuilderContext::getParamCtClass)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toArray(CtClass[]::new);
    }
}

