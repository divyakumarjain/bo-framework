package org.divy.common.bo.dynamic.clazz;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.divy.common.bo.dynamic.clazz.member.method.DynamicProxyMethodBuilderContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Random;

public class DynamicSubClassBuilderContext extends DynamicClassBuilderContext<DynamicSubClassBuilderContext> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicSubClassBuilderContext.class);
    private Class<?> parentClass;

    DynamicSubClassBuilderContext(Class<?> parentClass) {
        this.parentClass = parentClass;
    }

    DynamicSubClassBuilderContext(Class<?> parentClass, String className) {
        super(className);
        this.parentClass = parentClass;
    }

    public DynamicSubClassBuilderContext(Class<?> parentClass, String className, DynamicClassBuilderContext dynamicClassBuilderContext) {
        super(className, dynamicClassBuilderContext);
        this.parentClass = parentClass;
    }


    @Override
    protected void doBuild(CtClass newClass) throws CannotCompileException, NotFoundException {
        final CtClass superClass = getClassPool().getCtClass(parentClass.getName());
        newClass.setSuperclass(superClass);
        super.doBuild(newClass);
    }

    @Override
    String getClassName() {
        String className = super.getClassName();
        if(className==null) {
            return parentClass.getSimpleName() + new Random().nextInt();
        } else {
            return className;
        }
    }

    public DynamicProxyMethodBuilderContext proxySuperMethod(String methodName) {

        return getCtClass(parentClass).map(parentCtClass-> getDeclaredMethod(methodName, parentCtClass))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map((method) -> {
                    DynamicProxyMethodBuilderContext dynamicProxyMethodBuilderContext = new DynamicProxyMethodBuilderContext(this, method);
                    this.methods.add(dynamicProxyMethodBuilderContext);
                    return dynamicProxyMethodBuilderContext;
                })
                .orElseGet(() -> new DynamicProxyMethodBuilderContext(this));
    }

    private Optional<CtMethod> getDeclaredMethod(String methodName, CtClass parentCtClass) {
        try {
            return Optional.of(parentCtClass.getDeclaredMethod(methodName));
        } catch (NotFoundException e) {
            LOGGER.warn("Could not Find method {}(...) in class {}",methodName, parentCtClass.getName());
            LOGGER.debug("Could not Find method {}(...) in class {}",methodName, parentCtClass.getName(), e);
            return getSuperclass(parentCtClass)
                    .map(superClass -> getDeclaredMethod(methodName, superClass).get());

        }
    }

    private Optional<CtClass> getSuperclass(CtClass parentCtClass) {
        try {
            return Optional.ofNullable(parentCtClass.getSuperclass());
        } catch (NotFoundException e) {
            LOGGER.debug("Could not find super class for {}", parentCtClass.getName(), e);
            return Optional.empty();
        }
    }
}
