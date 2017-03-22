package org.divy.common.bo.dynamic.clazz.common;

import org.divy.common.bo.dynamic.clazz.DynamicBuilderContext;

import java.util.HashSet;
import java.util.Set;

public class DynamicAnnotatableBuilderContext<C extends DynamicAnnotatableBuilderContext, P extends DynamicBuilderContext> extends DynamicBuilderContext<P>{
    protected Set<DynamicAnnotationBuilderContext<C>> annotations = new HashSet<>();

    public DynamicAnnotatableBuilderContext(P parentContext) {
        super(parentContext);
    }

    public DynamicAnnotationBuilderContext<C> addAnnotation(Class<?> annotationClass) {
        final DynamicAnnotationBuilderContext<C> annotationBuilderContext = new DynamicAnnotationBuilderContext<>((C) this, annotationClass);
        this.annotations.add(annotationBuilderContext);
        return annotationBuilderContext;
    }
}
