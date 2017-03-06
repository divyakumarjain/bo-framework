package org.divy.common.bo.dynamic.clazz;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.NotFoundException;

public class DynamicSubClassBuilderContext extends DynamicClassBuilderContext {

    private Class<?> parentClass;

    DynamicSubClassBuilderContext(Class<?> parentClass) {
        this.parentClass = parentClass;
    }

    DynamicSubClassBuilderContext(Class<?> parentClass, String className) {
        super(className);
        this.parentClass = parentClass;
    }


    @Override
    protected void doBuild(CtClass newClass) throws CannotCompileException, NotFoundException {
        final CtClass superClass = getClassPool().getCtClass(parentClass.getName());
        newClass.setSuperclass(superClass);
        super.doBuild(newClass);
    }
}
