package org.divy.common.bo.dynamic.clazz.member.field;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtField;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilderContext;
import org.divy.common.bo.dynamic.clazz.common.DynamicAnnotatableBuilderContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicClassFieldBuilderContext
        extends DynamicAnnotatableBuilderContext<DynamicClassFieldBuilderContext, DynamicClassBuilderContext> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicClassFieldBuilderContext.class);
    private final String fieldName;
    private final Class<?> fieldTypeClass;

    public DynamicClassFieldBuilderContext(DynamicClassBuilderContext builderContext, String fieldName, Class<?> fieldTypeClass) {
        super(builderContext);
        this.fieldName = fieldName;
        this.fieldTypeClass = fieldTypeClass;
    }

    public void doBuild(CtClass newClass) {
        final String src = declarationCode();
        final CtField ctField;
        try {
            ctField = CtField.make(src, newClass);
            newClass.addField(ctField);
        } catch (CannotCompileException e) {
            LOGGER.error("Could not add field", e);
        }
    }

    private String declarationCode() {
        return fieldTypeClass.getName() + " " + fieldName +";";
    }

}
