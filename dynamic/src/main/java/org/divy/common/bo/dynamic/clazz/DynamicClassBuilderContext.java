package org.divy.common.bo.dynamic.clazz;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.Modifier;
import javassist.NotFoundException;
import org.divy.common.bo.dynamic.clazz.common.DynamicAnnotatableBuilderContext;
import org.divy.common.bo.dynamic.clazz.member.constructor.DynamicClassConstructorBuilderContext;
import org.divy.common.bo.dynamic.clazz.member.field.DynamicClassFieldBuilderContext;
import org.divy.common.bo.dynamic.clazz.member.method.DynamicMethodBuilderContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class DynamicClassBuilderContext<C extends DynamicClassBuilderContext>
        extends DynamicAnnotatableBuilderContext<C, DynamicClassBuilderContext> {

    private String className;
    private Set<DynamicClassConstructorBuilderContext> constructors = new HashSet<>();
    Set<DynamicMethodBuilderContext> methods = new HashSet<>();
    private Set<DynamicClassFieldBuilderContext> fields = new HashSet<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicClassBuilderContext.class);

    DynamicClassBuilderContext() {
        super(null);
    }
    DynamicClassBuilderContext(String className) {
        super(null);
        this.className = className;
    }

    DynamicClassBuilderContext(DynamicClassBuilderContext dynamicClassBuilderContext) {
        super(null);
        this.className = dynamicClassBuilderContext.className;

        this.annotations.addAll(dynamicClassBuilderContext.annotations);

        this.constructors.addAll(dynamicClassBuilderContext.constructors);

        this.fields.addAll(dynamicClassBuilderContext.fields);
    }

    public DynamicClassBuilderContext(String className, DynamicClassBuilderContext dynamicClassBuilderContext) {
        this(dynamicClassBuilderContext);
        this.className = className;
    }

    public DynamicClassBuilderContext<C> name(String className) {
        this.className = className;
        return this;
    }

    public DynamicSubClassBuilderContext subClass(Class<?> parentClass) {
        return new DynamicSubClassBuilderContext(parentClass, this.className, this);
    }

    public DynamicClassConstructorBuilderContext<C> addConstructor() {
        final DynamicClassConstructorBuilderContext dynamicClassConstructorBuilderContext = new DynamicClassConstructorBuilderContext(this);
        this.constructors.add(dynamicClassConstructorBuilderContext);
        return dynamicClassConstructorBuilderContext;
    }

    @Override
    public Optional<Class<?>> build() {

        CtClass newClass = getClassPool().makeClass(this.getClassName());
        try {
            doBuild(newClass);
//            newClass.debugWriteFile();
            return Optional.of(newClass.toClass());
        } catch (CannotCompileException | NotFoundException e) {
            LOGGER.error("Could not create the class", e);
            return Optional.empty();
        }
    }


    protected void doBuild(CtClass newClass) throws CannotCompileException, NotFoundException {
        newClass.setModifiers(Modifier.PUBLIC);
        this.annotations.forEach(annotationBuilderContext -> annotationBuilderContext.doBuild(newClass));
        this.fields.forEach(fieldBuilderContext -> fieldBuilderContext.doBuild(newClass));
        this.constructors.forEach(constructorBuilderContext -> constructorBuilderContext.doBuild(newClass));
        this.methods.forEach(dynamicMethodBuilderContext -> dynamicMethodBuilderContext.doBuild(newClass));
    }

    String getClassName() {
        return className;
    }

    public DynamicClassBuilderContext addField(String fieldName, Class<?> fieldTypeClass) {
        this.fields.add(new DynamicClassFieldBuilderContext(this,fieldName, fieldTypeClass));
        return this;
    }

    @Override
    public DynamicClassBuilderContext and() {
        throw new UnsupportedOperationException("And method does not support in Class Builder Context");
    }
}
