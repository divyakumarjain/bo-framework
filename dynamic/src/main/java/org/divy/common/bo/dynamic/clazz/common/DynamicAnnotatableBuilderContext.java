package org.divy.common.bo.dynamic.clazz.common;

import org.divy.common.bo.dynamic.clazz.DynamicBuilderContext;

import java.util.HashSet;
import java.util.Set;

public class DynamicAnnotatableBuilderContext<T extends DynamicAnnotatableBuilderContext, P extends DynamicBuilderContext> extends DynamicBuilderContext<P>{
    protected Set<DynamicAnnotationBuilderContext<T>> annotations = new HashSet<>();

    public DynamicAnnotatableBuilderContext(P parentContext) {
        super(parentContext);
    }

    public DynamicAnnotationBuilderContext<T> addAnnotation(Class<?> annotationClass) {
        final DynamicAnnotationBuilderContext<T> annotationBuilderContext = new DynamicAnnotationBuilderContext<>((T) this, annotationClass);
        this.annotations.add(annotationBuilderContext);
        return annotationBuilderContext;
    }
}
