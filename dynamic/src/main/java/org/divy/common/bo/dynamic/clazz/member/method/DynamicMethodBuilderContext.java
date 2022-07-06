package org.divy.common.bo.dynamic.clazz.member.method;

import javassist.*;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilderContext;
import org.divy.common.bo.dynamic.clazz.member.DynamicMemberBuilderContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DynamicMethodBuilderContext<P extends DynamicClassBuilderContext>
        extends DynamicMemberBuilderContext<DynamicMethodBuilderContext<P>, P> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicMethodBuilderContext.class);
    protected List<DynamicMethodParamBuilderContext<DynamicMethodBuilderContext<P>>> parameters = new ArrayList<>();
    private String body="";
    int paramCount=0;

    public DynamicMethodBuilderContext(P builderContext) {
        super(builderContext);
    }

    public DynamicMethodParamBuilderContext<DynamicMethodBuilderContext<P>> param(Class<?> paramClass) {

        DynamicMethodParamBuilderContext<DynamicMethodBuilderContext<P>> paramBuilderContext;
        if(paramCount<parameters.size()) {
            paramBuilderContext = parameters.get(paramCount);
        } else {
            paramBuilderContext = new DynamicMethodParamBuilderContext<>(this);
            parameters.add(paramBuilderContext);
            paramBuilderContext.index(paramCount);
        }

        paramBuilderContext.type(paramClass);

        paramCount++;
        return paramBuilderContext;
    }

    public DynamicMethodBuilderContext<P> body(String body) {
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
                .toArray(CtClass[]::new);
    }

    public P and() {
        return getParentContext();
    }
}

