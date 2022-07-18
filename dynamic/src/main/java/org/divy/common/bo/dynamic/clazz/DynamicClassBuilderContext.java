package org.divy.common.bo.dynamic.clazz;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.Modifier;
import org.divy.common.bo.dynamic.clazz.common.DynamicAnnotatableBuilderContext;
import org.divy.common.bo.dynamic.clazz.member.constructor.DynamicClassConstructorBuilderContext;
import org.divy.common.bo.dynamic.clazz.member.field.DynamicClassFieldBuilderContext;
import org.divy.common.bo.dynamic.clazz.member.method.DynamicMethodBuilderContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class DynamicClassBuilderContext
        extends DynamicAnnotatableBuilderContext<DynamicClassBuilderContext, DynamicClassBuilderContext> {

    private String className;
    private final Set<DynamicClassConstructorBuilderContext<DynamicClassBuilderContext>> constructors = new HashSet<>();
    private final Set<DynamicMethodBuilderContext<DynamicClassBuilderContext>> methods = new HashSet<>();
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

    public DynamicClassBuilderContext name(String className) {
        this.className = className;
        return this;
    }

    public DynamicSubClassBuilderContext subClass(Class<?> parentClass) {
        return new DynamicSubClassBuilderContext(parentClass, this.className, this);
    }

    public DynamicClassConstructorBuilderContext<DynamicClassBuilderContext> addConstructor() {
        final var dynamicClassConstructorBuilderContext = new DynamicClassConstructorBuilderContext<>(this);
        this.constructors.add(dynamicClassConstructorBuilderContext);
        return dynamicClassConstructorBuilderContext;
    }

    public DynamicMethodBuilderContext<DynamicClassBuilderContext> addMethod() {
        final DynamicMethodBuilderContext<DynamicClassBuilderContext> dynamicMethodBuilderContext = new DynamicMethodBuilderContext<>(this);
        this.methods.add(dynamicMethodBuilderContext);
        return dynamicMethodBuilderContext;
    }

    @Override
    public Optional<Class<?>> build( MethodHandles.Lookup lookup ) {

        CtClass newClass = getClassPool().makeClass(this.getClassName());
        try {
            doBuild(newClass);
            return Optional.of(newClass.toClass( lookup ));

        } catch ( CannotCompileException e) {
            LOGGER.error("Could not create the class", e);
            return Optional.empty();
        }
    }
    protected void doBuild(CtClass newClass) {
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

    protected void addMethods( DynamicMethodBuilderContext<DynamicClassBuilderContext> dynamicMethodBuilderContext )
    {
        this.methods.add(dynamicMethodBuilderContext);
    }
}
