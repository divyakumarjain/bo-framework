package org.divy.common.bo.dynamic;

import org.divy.common.bo.dynamic.clazz.DynamicClassBuilder;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
public class DynamicClassBuilderTest {

    @Test
    public void createClass() {
        final Optional<Class<?>> newClass = DynamicClassBuilder.createClass("NewClass")
                .build();

        assertThat(newClass.get().getSimpleName(), is("NewClass"));
    }

    @Test
    public void createClassWithConstructor() throws NoSuchMethodException {
        final Optional<Class<?>> newClass = DynamicClassBuilder.createClass("NewClassWithConstructor")
                .addConstructor()
                    .param("attribute1", String.class)
                        .addAnnotation(NewAnnotation1.class)
                            .value("NewAnnotation1Value")
                            .and()
                        .and()
                    .body("")
                    .addAnnotation(NewAnnotation1.class).value("methodAnnotaion")
                        .and()
                    .and()
                .addField("attribute1", String.class)
                .build();

        assertThat(newClass.get().getSimpleName(), is("NewClassWithConstructor"));

        final Constructor<?> constructor = newClass.get().getConstructor(String.class);
        assertThat(constructor, notNullValue());
        assertThat(constructor.getParameterAnnotations()[0][0], notNullValue());
        assertThat(constructor.getAnnotations(), arrayContainingInAnyOrder(is(instanceOf(NewAnnotation1.class))));
    }

    @Test
    public void createClassWithAnnotation() {
        final Optional<Class<?>> newClassWithAnnotation = DynamicClassBuilder.createClass("NewClassWithSingleAnnotation")
                .addAnnotation(NewAnnotation1.class)
                .value("NewAnnotation1Value")
                .build();

        assertThat(newClassWithAnnotation.get().getAnnotation(NewAnnotation1.class), notNullValue());

        assertThat(newClassWithAnnotation.get().getAnnotation(NewAnnotation1.class)
                .value(), is("NewAnnotation1Value"));
    }

    @Test
    public void createClassWithTwoAnnotation() {
        final Optional<Class<?>> newClassWithAnnotation = DynamicClassBuilder.createClass("NewClassWithTwoAnnotation")
                .addAnnotation(NewAnnotation1.class)
                    .value("NewAnnotation1Value")
                    .and()
                .addAnnotation(NewAnnotation2.class)
                .param("value", "NewAnnotation2Value")
                .build();

        assertThat(newClassWithAnnotation.get().getAnnotation(NewAnnotation1.class), notNullValue());

        assertThat(newClassWithAnnotation.get().getAnnotation(NewAnnotation1.class)
                .value(), is("NewAnnotation1Value"));

        assertThat(newClassWithAnnotation.get().getAnnotation(NewAnnotation2.class), notNullValue());

        assertThat(newClassWithAnnotation.get().getAnnotation(NewAnnotation2.class)
                .value(), is("NewAnnotation2Value"));


    }

    @Test
    public void subClass() {
        final Optional<Class<?>> aSubClass = DynamicClassBuilder.createSubClass(BaseClass.class)
                .name("SubClass")
                .build();

        assertThat(aSubClass.get(), both(typeCompatibleWith(BaseClass.class)).and(notNullValue()));
    }

    @Test
    public void subClassWithName() {
        final Optional<Class<?>> aSubClass = DynamicClassBuilder.createClass("SubClassWithName")
                .subClass(BaseClass.class)
                .build();

        assertThat(aSubClass.get().getSimpleName(), is("SubClassWithName"));
        assertThat(aSubClass.get(), both(typeCompatibleWith(BaseClass.class)).and(notNullValue()));
    }

    @Test
    public void subClassWithBaseConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final Optional<Class<?>> optionalResult = DynamicClassBuilder.createClass("SubClassWithBaseConstructor")
                .subClass(BaseClass.class)
                    .addConstructor()
                        .superParam(String.class)
                        .and()
                        .superParam(String.class)
                    .and()
                .build();

        final Class<?> subClass = optionalResult.get();
        assertThat(subClass, both(typeCompatibleWith(BaseClass.class)).and(notNullValue()));
        final Constructor<?> constructor = subClass.getConstructor(String.class, String.class);
        assertThat(constructor, notNullValue());

        final BaseClass instance = (BaseClass)constructor.newInstance("attribute1", "attribute2");

        assertThat(instance, hasProperty("attribute1", is("attribute1")));
        assertThat(instance, hasProperty("attribute2", is("attribute2")));
    }

    @Test
    public void subClassWithConstantValueForBaseConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final Optional<Class<?>> aSubClass = DynamicClassBuilder.createClass("SubClassWithConstantValueForBaseConstructor")
                .subClass(BaseClass.class)
                    .addConstructor()
                        .superParam(String.class)
                        .and()
                        .superValue("attribute2")
                    .and()
                .build();

        assertThat(aSubClass.get(), both(typeCompatibleWith(BaseClass.class)).and(notNullValue()));
        final Constructor<?> constructor = aSubClass.get().getConstructor(String.class);
        assertThat(aSubClass.get().getConstructor(String.class), notNullValue());

        final BaseClass instance = (BaseClass)constructor.newInstance("attribute1");

        assertThat(instance, hasProperty("attribute1", is("attribute1")));
        assertThat(instance, hasProperty("attribute2", is("attribute2")));
    }

    @ExistingAnnotation("ExistingValue")
    public static class BaseClass {
        protected String attribute1;
        protected String attribute2;

        public BaseClass(String attribute1, String attribute2) {
            this.attribute1 = attribute1;
            this.attribute2 = attribute2;
        }

        public String getAttribute1() {
            return attribute1;
        }

        public String getAttribute2() {
            return attribute2;
        }
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ExistingAnnotation {
        String value();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface NewAnnotation1 {
        String value();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface NewAnnotation2 {
        String value();
    }
}