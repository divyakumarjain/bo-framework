package org.divy.common.bo.dynamic.test;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class MethodParameterMatcher extends TypeSafeDiagnosingMatcher<Method> {
    final Class<?> parameterType;
    MethodParameterMatcher(Class<?> parameterType) {
        this.parameterType = parameterType;
    }

    @Override
    protected boolean matchesSafely(Method item, Description mismatch) {
        var matchedParameters = matchingParameters(item);
        if(matchedParameters.isEmpty()) {
            mismatch.appendText("No ").appendDescriptionOf(this);
            return false;
        }
        return true;
    }

    protected ArrayList<Parameter> matchingParameters(Method item) {
        var matchedParameters = new ArrayList<Parameter>();
        for (var parameter : item.getParameters()) {
            if(parameter.getType().isAssignableFrom(this.parameterType))
                matchedParameters.add(parameter);
        }
        return matchedParameters;
    }


    @Override
    public void describeTo(Description description) {
        description.appendText("hasParameter(").appendValue(this.parameterType)
                .appendText(")");
    }

    public static MethodParameterMatcher hasParameter(Class<?> parameterType) {
        return new MethodParameterMatcher(parameterType);
    }
}