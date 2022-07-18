package org.divy.common.bo.dynamic.test;

import org.hamcrest.Condition;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.collection.HasItemInArray;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class MethodParameterWithAnnotationMatcher extends MethodParameterMatcher {
    private final Matcher<Parameter> parameterMatcher;

    MethodParameterWithAnnotationMatcher(Class<?> parameterType, Matcher<Parameter> parameterMatcher) {
        super(parameterType);
        this.parameterMatcher = parameterMatcher;
    }

    @Override
    protected boolean matchesSafely(Method item, Description mismatch) {
        return parameterOn(item, mismatch)
                .matching(new HasItemInArray<>(this.parameterMatcher));
    }

    private Condition<Parameter[]> parameterOn(Method item, Description mismatch) {
        var matchedParameters = matchingParameters(item);
        if(matchedParameters.isEmpty()) {
            mismatch.appendText("No ").appendDescriptionOf(this);
            return Condition.notMatched();
        }
        var objects = matchedParameters.toArray(new Parameter[0]);
        return Condition.matched(objects, mismatch);
    }



    @Override
    public void describeTo(Description description) {
        description.appendText("hasParameter(")
                .appendValue(this.parameterType)
                .appendText(", ")
                .appendDescriptionOf(this.parameterMatcher)
                .appendText(")");
    }

    public static Matcher<Method> hasParameter(Class<?> parameterType, Matcher<Parameter> parameterMatcher) {
        return new MethodParameterWithAnnotationMatcher(parameterType, parameterMatcher);
    }
}
