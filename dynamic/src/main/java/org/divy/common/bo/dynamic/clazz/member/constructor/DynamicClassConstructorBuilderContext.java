package org.divy.common.bo.dynamic.clazz.member.constructor;

import javassist.*;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilderContext;
import org.divy.common.bo.dynamic.clazz.common.ConstantInitializationValueProvider;
import org.divy.common.bo.dynamic.clazz.common.DynamicInitializationValueProvider;
import org.divy.common.bo.dynamic.clazz.member.method.DynamicMethodBuilderContext;
import org.divy.common.bo.dynamic.clazz.member.method.DynamicMethodParamBuilderContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DynamicClassConstructorBuilderContext<P extends DynamicClassBuilderContext>
        extends DynamicMethodBuilderContext<P> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicClassConstructorBuilderContext.class);
    private final List<DynamicConstructorBehaviourContext<P>> superBehaviour = new ArrayList<>();
    private final List<DynamicConstructorBehaviourContext<P>> fieldBehaviour = new ArrayList<>();
    private String body="";

    public DynamicClassConstructorBuilderContext(P builderContext) {
        super(builderContext);
        type(Void.class);
    }

    public DynamicMethodParamBuilderContext<DynamicMethodBuilderContext<P>> param(String fieldName, Class<?> paramClass) {
        final DynamicMethodParamBuilderContext<DynamicMethodBuilderContext<P>> param = super.param(paramClass);

        final DynamicConstructorFieldInitializer<P> paramBuilderContext = new DynamicConstructorFieldInitializer<>(this,fieldName, paramClass, param);
        superBehaviour.add(paramBuilderContext);
        return param;
    }


    public DynamicMethodParamBuilderContext<DynamicMethodBuilderContext<P>> superParam(Class<?> paramClass) {
        var param = super.param(paramClass);
        final var superInitializer = new DynamicConstructorSuperInitializer<P>(this, paramClass, param);
        superBehaviour.add(superInitializer);
        return param;
    }

    public DynamicConstructorSuperInitializer<P> superValue(Object value) {
        DynamicInitializationValueProvider param = new ConstantInitializationValueProvider(value);
        final DynamicConstructorSuperInitializer<P> paramBuilderContext
                = new DynamicConstructorSuperInitializer<>(this, value.getClass(), param);
        superBehaviour.add(paramBuilderContext);
        return paramBuilderContext;
    }


    @Override
    public DynamicClassConstructorBuilderContext<P> body(String body) {
        this.body = body;
        return this;
    }

    @Override
    public DynamicClassConstructorBuilderContext<P> name(String methodName) {
        throw new UnsupportedOperationException("Constructor cannot have name");
    }
    @Override
    public void doBuild(CtClass newClass) {
        try {
            CtConstructor constructor = CtNewConstructor.make(getParameters()
                    , getExceptions()
                    , getConstructorBody()
                    , newClass);
            constructor.setModifiers(Modifier.PUBLIC);
            newClass.addConstructor(constructor);
            parameters
                    .forEach(param -> param.doBuild(constructor));
            doBuild(constructor);
        } catch (CannotCompileException e) {
            LOGGER.error("Could not add method to the class", e);
        }
    }

    private String getConstructorBody() {
        return "{" + createSuperBlock() +
                createFieldInitializeBlock() +
                body +
                "}";
    }

    private String createFieldInitializeBlock() {
        return fieldBehaviour.stream()
                .filter(DynamicConstructorFieldInitializer.class::isInstance)
                .map(DynamicConstructorFieldInitializer.class::cast)
                .map(DynamicConstructorFieldInitializer::code)
                .collect(Collectors.joining(";"+System.lineSeparator()));
    }

    private String createSuperBlock() {
        return "super(" + superBehaviour.stream()
                .filter(DynamicConstructorSuperInitializer.class::isInstance)
                .map(DynamicConstructorSuperInitializer.class::cast)
                .map(DynamicConstructorSuperInitializer::code)
                .collect(Collectors.joining(", "))
        + ");";
    }

    @Override
    public P and() {
        return getParentContext();
    }
}
