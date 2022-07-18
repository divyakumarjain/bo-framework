package org.divy.common.bo.dynamic.test;


import org.hamcrest.Condition;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;

public class AnnotationWithParameterMatcher<T extends AnnotatedElement> extends AnnotationMatcher<T> {
    private final Matcher<Annotation> parameterMatcher;
    public AnnotationWithParameterMatcher(Class<? extends Annotation> annotationClass, Matcher<Annotation> parameterMatcher) {
        super(annotationClass);
        this.parameterMatcher = parameterMatcher;
    }
    @Override
    protected boolean matchesSafely(T item, Description mismatch) {
        return annotationOn(item, mismatch)
                .matching(parameterMatcher);
    }
    private Condition<Annotation> annotationOn(T item, Description mismatch) {
        Annotation annotation = item.getAnnotation(this.annotationClass);
        if(annotation == null) {
            mismatch.appendText("No Annotation ").appendValue(this.annotationClass);
            return Condition.notMatched();
        }
        return Condition.matched(annotation, mismatch);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("isAnnotatedWith(").appendValue(this.annotationClass)
                .appendText(", ")
                .appendDescriptionOf(this.parameterMatcher)
                .appendText(")");
    }
    public static <T extends AnnotatedElement>  Matcher<T> isAnnotatedWith(Class<? extends Annotation> annotationClass, Matcher<Annotation> parameterMatcher ) {
        return new AnnotationWithParameterMatcher<>(annotationClass, parameterMatcher);
    }

    public static <A extends Annotation, V> Matcher<A> value(Matcher<V> valueMatcher ) {
        return hasAnnotationParameter("value", valueMatcher);
    }
    public static <A extends Annotation, V> Matcher<A> hasAnnotationParameter(String value, Matcher<V> valueMatcher) {
        return new AnnotationParameterMatcher<>(value,valueMatcher);
    }
    static class AnnotationParameterMatcher<A extends Annotation, V> extends TypeSafeDiagnosingMatcher<A> {

        private final String parameterName;
        private final Matcher<V> valueMatcher;

        AnnotationParameterMatcher(String parameterName, Matcher<V> valueMatcher) {
            this.parameterName = parameterName;
            this.valueMatcher = valueMatcher;
        }

        @Override
        protected boolean matchesSafely(A item, Description mismatch) {
            try {
                Object parameterValue = item.getClass().getMethod(this.parameterName).invoke(item);
                if(valueMatcher.matches(parameterValue))
                    return true;
                else{
                    mismatch.appendText("Annotation Parameter ").appendValue(this.parameterName).appendText(" ");
                    valueMatcher.describeMismatch(item, mismatch);
                }
                return false;
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                mismatch.appendValue(e);
                return false;
            }
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("hasAnnotationParameter(").appendValue(parameterName).appendText("=");
            valueMatcher.describeTo(description);
            description.appendText(")");
        }
    }
}
