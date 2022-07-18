package org.divy.common.bo.dynamic.clazz;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.divy.common.bo.dynamic.DynamicClassException;
import org.divy.common.bo.dynamic.clazz.member.method.DynamicProxyMethodBuilderContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Optional;

public class DynamicSubClassBuilderContext extends DynamicClassBuilderContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicSubClassBuilderContext.class);
    private final Class<?> parentClass;

    DynamicSubClassBuilderContext(Class<?> parentClass) {
        this.parentClass = parentClass;
    }

    public DynamicSubClassBuilderContext(Class<?> parentClass, String className, DynamicClassBuilderContext dynamicClassBuilderContext) {
        super(className, dynamicClassBuilderContext);
        this.parentClass = parentClass;
    }
    @Override
    protected void doBuild(CtClass newClass) {
        final CtClass superClass;
        try
        {
            superClass = getClassPool(parentClass).getCtClass(parentClass.getName());
            newClass.setSuperclass(superClass);
        } catch (NotFoundException e)
        {
            LOGGER.error("Could not find parent Class" + parentClass.getName() + " for " + newClass.getName(),e);
        } catch (CannotCompileException e) {
            throw new DynamicClassException(e);
        }
        super.doBuild(newClass);
    }

    @Override
    String getClassName() {
        String className = super.getClassName();
        return Objects.requireNonNullElseGet(className, () -> parentClass.getSimpleName() + new SecureRandom().nextInt());
    }

    public DynamicProxyMethodBuilderContext proxySuperMethod(String methodName) {

        return getCtClass(parentClass).map(parentCtClass-> getDeclaredMethod(methodName, parentCtClass))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(method -> {
                    var dynamicProxyMethodBuilderContext = new DynamicProxyMethodBuilderContext(this, method);
                    this.addMethods(dynamicProxyMethodBuilderContext);
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
