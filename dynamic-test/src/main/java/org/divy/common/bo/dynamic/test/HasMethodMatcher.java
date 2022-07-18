package org.divy.common.bo.dynamic.test;


import org.hamcrest.Condition;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.collection.HasItemInArray;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("java:S3740")
public class HasMethodMatcher extends TypeSafeDiagnosingMatcher<Class> {
    private final String methodName;
    private final Matcher<Method> parameterTypes;

    private final Matcher<Method[]> methodMatcher;

    public HasMethodMatcher(String methodName, Matcher<Method> parameterTypes) {
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.methodMatcher = null;
    }

    public HasMethodMatcher(String methodName, Matcher<Method> parameterTypes, Matcher<Method> methodMatcher) {
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.methodMatcher = new HasItemInArray<>(methodMatcher);
    }

    @Override
    protected boolean matchesSafely(Class item, Description mismatch) {
        return methodOn(item, mismatch)
                .and(matchParameter())
                .matching(methodMatcher);
    }

    private Condition.Step<Method[], Method[]> matchParameter() {
        return (methods, mismatch) -> {
            List<Method> matchedMethods = new ArrayList<>();
            if(parameterTypes != null){
                for (Method method : methods) {
                    if(parameterTypes.matches(method))
                        matchedMethods.add(method);
                }
                if(matchedMethods.isEmpty()) {
                    mismatch.appendText("No Method ").appendDescriptionOf(parameterTypes);
                    return Condition.notMatched();
                }
            } else {
                matchedMethods.addAll(List.of(methods));
            }
            return Condition.matched(matchedMethods.toArray(new Method[0]), mismatch);
        };
    }

    private Condition<Method[]> methodOn(Class<?> item, Description mismatch) {
        List<Method> methods = new ArrayList<>();
        for (Method itemMethod : item.getDeclaredMethods()) {
            if(itemMethod.getName().equals(this.methodName))
                methods.add(itemMethod);
        }
        if(methods.isEmpty()) {
            mismatch.appendText("No ").appendDescriptionOf(this);
            return Condition.notMatched();
        }
        return Condition.matched(methods.toArray(new Method[0]), mismatch);
    }

    public void describeTo(Description description) {
        description.appendText("hasMethod(").appendValue(this.methodName)
                .appendText(", ");
        if(this.parameterTypes !=null){
            description.appendDescriptionOf(this.parameterTypes)
                    .appendText(", ");
        }
        description.appendDescriptionOf(this.methodMatcher)
                .appendText(")");
    }

    @SuppressWarnings("java:S3740")
    public static  Matcher<Class> hasMethod(String methodName, Matcher<Method> parameterTypes) {
        return new HasMethodMatcher(methodName, parameterTypes);
    }
    @SuppressWarnings("java:S3740")
    public static  Matcher<Class> hasMethod(String methodName, Matcher<Method> parameterTypes, Matcher<Method> methodMatcher) {
        return new HasMethodMatcher(methodName, parameterTypes, methodMatcher);
    }
}
