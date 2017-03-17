package org.divy.common.bo.dynamic.clazz.common;

import javassist.CtBehavior;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.*;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.StringMemberValue;
import org.divy.common.bo.dynamic.clazz.DynamicBuilderContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DynamicAnnotationBuilderContext<P extends DynamicAnnotatableBuilderContext> extends DynamicBuilderContext<P> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicAnnotationBuilderContext.class);
    private final Class<?> annotationClass;
    private final Map<String, Object> annotationParam = new HashMap<>();

    DynamicAnnotationBuilderContext(P parentContext, Class<?> annotationClass) {
        super(parentContext);
        this.annotationClass = annotationClass;
    }

    public DynamicAnnotationBuilderContext<P> value(Object value) {
        annotationParam.put("value", value);
        return this;
    }

    public DynamicAnnotationBuilderContext<P> param(String paramName, Object value) {
        annotationParam.put(paramName, value);
        return this;
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
        this.annotationParam.forEach((paramName, paramValue) -> annot.addMemberValue(paramName, doBuildAnnotationParamValue(paramValue, constPool)));
    }

    private MemberValue doBuildAnnotationParamValue(Object paramValue, ConstPool constPool) {
        if (paramValue instanceof String) {
            return new StringMemberValue((String) paramValue, constPool);
        } else {
            throw new UnsupportedOperationException("Only String Dynamic Annotation are supported");
        }
    }

    private Class<?> getAnnotationClass() {
        return annotationClass;
    }

    public Map<String, Object> getAnnotationParam() {
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
            Annotation[] addAnno = paramArrays[index-1];
            Annotation[] newAnno;
            if(addAnno == null || addAnno.length == 0) {
                newAnno = new Annotation[1];
                newAnno[0] = parameterAnnotation;
            } else {
                newAnno = ensureArrayLength(addAnno, addAnno.length+1);
                newAnno[addAnno.length] = parameterAnnotation;
            }

            paramArrays[index-1] = newAnno;
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
