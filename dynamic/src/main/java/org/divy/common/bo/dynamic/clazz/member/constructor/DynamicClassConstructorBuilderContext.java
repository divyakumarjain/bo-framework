package org.divy.common.bo.dynamic.clazz.member.constructor;

import javassist.*;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilderContext;
import org.divy.common.bo.dynamic.clazz.common.ConstantInitializationValueProvider;
import org.divy.common.bo.dynamic.clazz.common.DynamicInitializationValueProvider;
import org.divy.common.bo.dynamic.clazz.member.DynamicMemberBuilderContext;
import org.divy.common.bo.dynamic.clazz.member.method.DynamicMethodBuilderContext;
import org.divy.common.bo.dynamic.clazz.member.method.DynamicMethodParamBuilderContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DynamicClassConstructorBuilderContext extends DynamicMethodBuilderContext<DynamicClassConstructorBuilderContext> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicClassConstructorBuilderContext.class);
    private List<DynamicConstructorBehaviourContext> superBehaviour = new ArrayList<>();
    private List<DynamicConstructorBehaviourContext> fieldBehaviour = new ArrayList<>();
    private String body="";

    public DynamicClassConstructorBuilderContext(DynamicClassBuilderContext builderContext) {
        super(builderContext);
        type(Void.class);
    }

    public DynamicMethodParamBuilderContext<DynamicClassConstructorBuilderContext> param(String fieldName, Class<?> paramClass) {
        final DynamicMethodParamBuilderContext<DynamicClassConstructorBuilderContext> param = super.param(paramClass);

        final DynamicConstructorFieldInitializer paramBuilderContext = new DynamicConstructorFieldInitializer(this,fieldName, paramClass, param);
        superBehaviour.add(paramBuilderContext);
        return param;
    }


    public DynamicMethodParamBuilderContext<DynamicClassConstructorBuilderContext> superParam(Class<?> paramClass) {
        final DynamicMethodParamBuilderContext<DynamicClassConstructorBuilderContext> param = super.param(paramClass);
        final DynamicConstructorSuperInitializer superInitializer
                = new DynamicConstructorSuperInitializer(this, paramClass, param);
        superBehaviour.add(superInitializer);
        return param;
    }

    public DynamicConstructorSuperInitializer superValue(Object value) {
        DynamicInitializationValueProvider param = new ConstantInitializationValueProvider(value);
        final DynamicConstructorSuperInitializer paramBuilderContext
                = new DynamicConstructorSuperInitializer(this, value.getClass(), param);
        superBehaviour.add(paramBuilderContext);
        return paramBuilderContext;
    }


    @Override
    public DynamicClassConstructorBuilderContext body(String body) {
        this.body = body;
        return this;
    }

    @Override
    public DynamicMemberBuilderContext name(String methodName) {
        throw new UnsupportedOperationException("Constructor cannot have name");
    }
    @Override
    public void doBuild(CtClass newClass) {
        try {
            CtConstructor constructor = CtNewConstructor.make(getParameters()
                    , getExceptions()
                    , getBody()
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

    private String getBody() {
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
}
