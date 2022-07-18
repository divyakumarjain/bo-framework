package org.divy.common.bo.dynamic.test;


import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

public class AnnotationMatcher<T extends AnnotatedElement> extends TypeSafeDiagnosingMatcher<T> {
    final Class<? extends Annotation> annotationClass;

    public AnnotationMatcher(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    @Override
    protected boolean matchesSafely(T item, Description mismatch) {
        Annotation annotation = item.getAnnotation(this.annotationClass);
        if(annotation == null) {
            mismatch.appendText("No Annotation ").appendValue(this.annotationClass);
            return false;
        }
        return true;
    }

    public void describeTo(Description description) {
        description.appendText("isAnnotatedWith(").appendValue(this.annotationClass)
                .appendText(")");
    }

    public static <T extends AnnotatedElement>  Matcher<T> isAnnotatedWith(Class<? extends Annotation> annotationClass) {
        return new AnnotationMatcher<>(annotationClass);
    }
}
