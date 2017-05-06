package org.divy.common.bo.dynamic.clazz.common;

import javassist.CtBehavior;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.*;
import javassist.bytecode.annotation.Annotation;
import org.divy.common.bo.dynamic.clazz.DynamicBuilderContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DynamicAnnotationBuilderContext<P extends DynamicAnnotatableBuilderContext> extends DynamicBuilderContext<P> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicAnnotationBuilderContext.class);
    private final Class<?> annotationClass;
    private final Map<String, DynamicAnnotationParam> annotationParam = new HashMap<>();

    DynamicAnnotationBuilderContext(P parentContext, Class<?> annotationClass) {
        super(parentContext);
        this.annotationClass = annotationClass;
    }

    public DynamicAnnotationBuilderContext<P> value(DynamicAnnotationParam value) {
        annotationParam.put("value", value);
        return this;
    }

    public DynamicAnnotationBuilderContext<P> value(String value) {
        return this.value(new StringAnnotationParam(value));
    }

    public DynamicAnnotationBuilderContext<P> value(String[] values) {
        return this.value(new StringArrayAnnotationParam(values));
    }


    public DynamicAnnotationBuilderContext<P> param(String paramName, DynamicAnnotationParam value) {
        annotationParam.put(paramName, value);
        return this;
    }

    public DynamicAnnotationBuilderContext<P> param(String paramName, String value) {
        return this.param(paramName, new StringAnnotationParam(value));
    }

    public DynamicAnnotationBuilderContext<P> param(String paramName, String[] values) {
        return this.param(paramName, new StringArrayAnnotationParam(values));
    }

    public DynamicAnnotationBuilderContext<P> param(String paramName, Enum value) {
        return this.param(paramName, new EnumAnnotationParam(value));
    }

    public void doBuild(CtClass newClassCt) {
        ClassFile classFile = newClassCt.getClassFile();
        ConstPool constPool = classFile.getConstPool();

        AnnotationsAttribute attribute = (AnnotationsAttribute) classFile.getAttribute(AnnotationsAttribute.visibleTag);
        if (attribute == null) {
            attribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
            classFile.addAttribute(attribute);
        }
        doBuildAttributeInfo(attribute, constPool);
    }

    private void doBuildAttributeInfo(AnnotationsAttribute attribute, ConstPool constPool) {
        Annotation annotation = new Annotation(annotationClass.getName(), constPool);

        doBuildAnnotationParam(annotation, constPool);
        attribute.addAnnotation(annotation);
    }

    private void doBuildAnnotationParam(Annotation annot, ConstPool constPool) {
        this.annotationParam.forEach((paramName, paramValue) -> annot.addMemberValue(paramName, paramValue.doBuildAnnotationParamValue(constPool)));
    }

    private Class<?> getAnnotationClass() {
        return annotationClass;
    }

    public Map<String, DynamicAnnotationParam> getAnnotationParam() {
        return annotationParam;
    }

    public void doBuild(CtBehavior behavior, int index) {
        try {
            final MethodInfo methodInfo = behavior.getMethodInfo();

            AttributeInfo paramAttributeInfo = methodInfo.getAttribute(ParameterAnnotationsAttribute.visibleTag); // or inVisibleTag
            if (paramAttributeInfo == null) {
                paramAttributeInfo = new ParameterAnnotationsAttribute(methodInfo.getConstPool(), ParameterAnnotationsAttribute.visibleTag);
                methodInfo.addAttribute(paramAttributeInfo);
            }
            ConstPool parameterConstPool = paramAttributeInfo.getConstPool();

            Annotation parameterAnnotation = new Annotation(getAnnotationClass().getName(), parameterConstPool);
            doBuildAnnotationParam(parameterAnnotation, parameterConstPool);


            ParameterAnnotationsAttribute parameterAttribute = (ParameterAnnotationsAttribute) paramAttributeInfo;
            Annotation[][] paramArrays = parameterAttribute.getAnnotations();

            paramArrays = initIfNot(paramArrays, behavior.getParameterTypes().length);
            Annotation[] addAnno = paramArrays[index];
            Annotation[] newAnno;
            if(addAnno == null || addAnno.length == 0) {
                newAnno = new Annotation[1];
                newAnno[0] = parameterAnnotation;
            } else {
                newAnno = ensureArrayLength(addAnno, addAnno.length+1);
                newAnno[addAnno.length] = parameterAnnotation;
            }

            paramArrays[index] = newAnno;
            parameterAttribute.setAnnotations(paramArrays);
        } catch (NotFoundException e) {
            LOGGER.error("Could not add annotation", e);
        }
    }

    private Annotation[][] initIfNot(Annotation[][] paramArrays, int length) {
        final Annotation[][] result;
        if(paramArrays==null) {
            result = new Annotation[length][];
        } else {
            result = ensureArrayLength(paramArrays, length);
        }

        for(int i=0;i<length;i++) {
            if(result[i] == null)
                result[i] = new Annotation[0];
        }
        return result;
    }

    private <T> T[] ensureArrayLength(T[] originalArray, int length) {
        if(originalArray.length <= length) {
            return Arrays.copyOf(originalArray, length);
        }
        return originalArray;
    }

    public void doBuild(CtBehavior behavior) {
        final MethodInfo methodInfo = behavior.getMethodInfo();


        AttributeInfo paramAttributeInfo = methodInfo.getAttribute(AnnotationsAttribute.visibleTag); // or inVisibleTag
        if (paramAttributeInfo == null) {
            paramAttributeInfo = new AnnotationsAttribute(methodInfo.getConstPool(), AnnotationsAttribute.visibleTag);
            methodInfo.addAttribute(paramAttributeInfo);
        }

        ConstPool parameterConstPool = paramAttributeInfo.getConstPool();
        Annotation annotation = new Annotation(annotationClass.getName(), parameterConstPool);

        doBuildAnnotationParam(annotation, parameterConstPool);
        ((AnnotationsAttribute)paramAttributeInfo).addAnnotation(annotation);
    }
}
