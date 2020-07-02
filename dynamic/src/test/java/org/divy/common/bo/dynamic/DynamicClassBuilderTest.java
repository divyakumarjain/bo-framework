package org.divy.common.bo.dynamic;

import org.divy.common.bo.dynamic.clazz.DynamicClassBuilder;
import org.junit.Before;
import org.junit.Test;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class DynamicClassBuilderTest {

    MethodHandles.Lookup prvlookup;

    @Before
    public void setup() throws IllegalAccessException
    {
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        this.getClass().getModule().addReads(this.getClass().getModule());

        prvlookup = MethodHandles.privateLookupIn(this.getClass(), lookup);
    }

    @Test
    public void createClass()
    {
        final Optional<Class<?>> newClass = DynamicClassBuilder.createClass("org.divy.common.bo.dynamic.NewClass")
                .build(prvlookup);

        assertThat(newClass.get().getName(), is("org.divy.common.bo.dynamic.NewClass"));
    }

    @Test
    public void createClassWithConstructor() throws NoSuchMethodException {
        final Optional<Class<?>> newClass = DynamicClassBuilder.createClass("org.divy.common.bo.dynamic.NewClassWithConstructor")
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
                .build( prvlookup );

        assertThat(newClass.get().getName(), is("org.divy.common.bo.dynamic.NewClassWithConstructor"));

        final Constructor<?> constructor = newClass.get().getConstructor(String.class);
        assertThat(constructor, notNullValue());
        assertThat(constructor.getParameterAnnotations()[0][0], notNullValue());
        assertThat(constructor.getAnnotations(), arrayContainingInAnyOrder(is(instanceOf(NewAnnotation1.class))));
    }

    @Test
    public void createClassWithAnnotation() {
        final Optional<Class<?>> newClassWithAnnotation = DynamicClassBuilder.createClass("org.divy.common.bo.dynamic.NewClassWithSingleAnnotation")
                .addAnnotation(NewAnnotation1.class)
                .value("NewAnnotation1Value")
                .build( prvlookup );

        assertThat(newClassWithAnnotation.get().getAnnotation(NewAnnotation1.class), notNullValue());

        assertThat(newClassWithAnnotation.get().getAnnotation(NewAnnotation1.class)
                .value(), is("NewAnnotation1Value"));
    }

    @Test
    public void createClassWithTwoAnnotation() {
        final Optional<Class<?>> newClassWithAnnotation = DynamicClassBuilder.createClass("org.divy.common.bo.dynamic.NewClassWithTwoAnnotation")
                .addAnnotation(NewAnnotation1.class)
                    .value("NewAnnotation1Value")
                    .and()
                .addAnnotation(NewAnnotation2.class)
                .param("value", "NewAnnotation2Value")
                .build( prvlookup );

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
                .name("org.divy.common.bo.dynamic.SubClass")
                .build(prvlookup);

        assertThat(aSubClass.get(), both(typeCompatibleWith(BaseClass.class)).and(notNullValue()));
    }

    @Test
    public void subClassWithName() {
        final Optional<Class<?>> aSubClass = DynamicClassBuilder.createClass("org.divy.common.bo.dynamic.SubClassWithName")
                .subClass(BaseClass.class)
                .build( prvlookup );

        assertThat(aSubClass.get().getSimpleName(), is("SubClassWithName"));
        assertThat(aSubClass.get(), both(typeCompatibleWith(BaseClass.class)).and(notNullValue()));
    }

    @Test
    public void subClassWithBaseConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final Optional<Class<?>> optionalResult = DynamicClassBuilder.createClass("org.divy.common.bo.dynamic.SubClassWithBaseConstructor")
                .subClass(BaseClass.class)
                    .addConstructor()
                        .superParam(String.class)
                        .and()
                        .superParam(String.class)
                    .and()
                .build( prvlookup );

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
        final Optional<Class<?>> aSubClass = DynamicClassBuilder.createClass("org.divy.common.bo.dynamic.SubClassWithConstantValueForBaseConstructor")
                .subClass(BaseClass.class)
                    .addConstructor()
                        .superParam(String.class)
                        .and()
                        .superValue("attribute2")
                    .and()
                .build( prvlookup );

        assertThat(aSubClass.get(), both(typeCompatibleWith(BaseClass.class)).and(notNullValue()));
        final Constructor<?> constructor = aSubClass.get().getConstructor(String.class);
        assertThat(aSubClass.get().getConstructor(String.class), notNullValue());

        final BaseClass instance = (BaseClass)constructor.newInstance("attribute1");

        assertThat(instance, hasProperty("attribute1", is("attribute1")));
        assertThat(instance, hasProperty("attribute2", is("attribute2")));
    }



}
