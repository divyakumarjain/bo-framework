package org.divy.common.bo.dynamic;

import org.divy.common.bo.dynamic.clazz.DynamicClassBuilder;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilderContext;
import org.divy.common.bo.dynamic.clazz.DynamicSubClassBuilderContext;
import org.divy.common.bo.dynamic.clazz.MemberVisibility;
import org.divy.common.bo.dynamic.clazz.member.constructor.DynamicClassConstructorBuilderContext;
import org.divy.common.bo.dynamic.clazz.member.method.DynamicProxyMethodBuilderContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
class DynamicClassBuilderTest {

    MethodHandles.Lookup prvlookup;

    @BeforeEach
    void setup() throws IllegalAccessException
    {
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        this.getClass().getModule().addReads(this.getClass().getModule());

        prvlookup = MethodHandles.privateLookupIn(this.getClass(), lookup);
    }

    @Test
    void createClass()
    {
        final Optional<Class<?>> newClass = DynamicClassBuilder.createClass("org.divy.common.bo.dynamic.NewClass")
                .build(prvlookup);

        assertThat(newClass.get().getName(),
                is("org.divy.common.bo.dynamic.NewClass"));
    }

    @Test
    void createClassWithConstructor() throws NoSuchMethodException {
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
    void createClassWithAnnotation() {
        final Optional<Class<?>> newClassWithAnnotation = DynamicClassBuilder.createClass("org.divy.common.bo.dynamic.NewClassWithSingleAnnotation")
                .addAnnotation(NewAnnotation1.class)
                .value("NewAnnotation1Value")
                .build( prvlookup );

        assertThat(newClassWithAnnotation.get().getAnnotation(NewAnnotation1.class), notNullValue());

        assertThat(newClassWithAnnotation.get().getAnnotation(NewAnnotation1.class)
                .value(), is("NewAnnotation1Value"));
    }

    @Test
    void createClassWithTwoAnnotation() {
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
    void subClass() {
        final Optional<Class<?>> aSubClass = DynamicClassBuilder.createSubClass(BaseClass.class)
                .name("org.divy.common.bo.dynamic.SubClass")
                .build(prvlookup);

        assertThat(aSubClass.get(), both(typeCompatibleWith(BaseClass.class)).and(notNullValue()));
    }

    @Test
    void subClassWithName() {
        final Optional<Class<?>> aSubClass = DynamicClassBuilder.createClass("org.divy.common.bo.dynamic.SubClassWithName")
                .subClass(BaseClass.class)
                .build( prvlookup );

        assertThat(aSubClass.get().getSimpleName(), is("SubClassWithName"));
        assertThat(aSubClass.get(), both(typeCompatibleWith(BaseClass.class)).and(notNullValue()));
    }

    @Test
    void subClassWithBaseConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        DynamicClassBuilderContext dynamicClass = DynamicClassBuilder.createClass("org.divy.common.bo.dynamic.SubClassWithBaseConstructor");

        DynamicClassConstructorBuilderContext<DynamicClassBuilderContext> constructorMethod = dynamicClass
                .subClass(BaseClass.class)
                .addConstructor();

        constructorMethod.superParam(String.class);

        constructorMethod.superParam(String.class);

        final Optional<Class<?>> optionalResult = constructorMethod.build( prvlookup );

        final Class<?> subClass = optionalResult.get();
        assertThat(subClass, both(typeCompatibleWith(BaseClass.class)).and(notNullValue()));
        final Constructor<?> constructor = subClass.getConstructor(String.class, String.class);
        assertThat(constructor, notNullValue());

        final BaseClass instance = (BaseClass)constructor.newInstance("attribute1", "attribute2");

        assertThat(instance, hasProperty("attribute1", is("attribute1")));
        assertThat(instance, hasProperty("attribute2", is("attribute2")));
    }

    @Test
    void subClassWithConstantValueForBaseConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        DynamicSubClassBuilderContext dynamicClassBuilderContext = DynamicClassBuilder.createClass("org.divy.common.bo.dynamic.SubClassWithConstantValueForBaseConstructor").subClass(BaseClass.class);

        DynamicClassConstructorBuilderContext<DynamicClassBuilderContext> dynamicClassBuilderContextDynamicClassConstructorBuilderContext = dynamicClassBuilderContext
                .addConstructor();

        dynamicClassBuilderContextDynamicClassConstructorBuilderContext
                        .superParam(String.class);
        dynamicClassBuilderContextDynamicClassConstructorBuilderContext
                        .superValue("attribute2");

        final Optional<Class<?>> aSubClass = dynamicClassBuilderContext.build(prvlookup);

        assertThat(aSubClass.get(), both(typeCompatibleWith(BaseClass.class)).and(notNullValue()));
        final Constructor<?> constructor = aSubClass.get().getConstructor(String.class);
        assertThat(aSubClass.get().getConstructor(String.class), notNullValue());

        final BaseClass instance = (BaseClass)constructor.newInstance("attribute1");

        assertThat(instance, hasProperty("attribute1", is("attribute1")));
        assertThat(instance, hasProperty("attribute2", is("attribute2")));
    }

    @Test
    void subClassWithProxySuperMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        DynamicClassBuilderContext dynamicClass = DynamicClassBuilder.createClass("org.divy.common.bo.dynamic.SubClassWithBaseConstructorAndSuperProxy");

        DynamicSubClassBuilderContext dynamicSubClassBuilderContext = dynamicClass
                .subClass(BaseClass.class);

        DynamicClassConstructorBuilderContext<DynamicClassBuilderContext> constructorMethod = dynamicSubClassBuilderContext
                .addConstructor();
        constructorMethod.superParam(String.class);
        constructorMethod.superParam(String.class);

        DynamicProxyMethodBuilderContext changeAttribute1MethodBuildContext = dynamicSubClassBuilderContext.proxySuperMethod("changeAttribute1");
        changeAttribute1MethodBuildContext.visibility(MemberVisibility.PUBLIC);
        changeAttribute1MethodBuildContext.name("subclassChangeAttribute1");

        final Optional<Class<?>> optionalResult = constructorMethod.build( prvlookup );

        final Class<?> subClass = optionalResult.get();
        final Constructor<?> constructor = subClass.getConstructor(String.class, String.class);

        final BaseClass instance = (BaseClass)constructor.newInstance("attribute1", "attribute2");

        Method changeAttribute1Method = subClass.getMethod("subclassChangeAttribute1", String.class);
        changeAttribute1Method.invoke(instance,"changedAttribute1");

        assertThat(instance, hasProperty("attribute1", is("changedAttribute1")));
        assertThat(instance, hasProperty("attribute2", is("attribute2")));
    }


    @Test
    void doubleSubClass() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        DynamicSubClassBuilderContext dynamicSubClassBuilderContext = DynamicClassBuilder
                .createClass("org.divy.common.bo.dynamic.FirstLevelSubClass")
                .subClass(BaseClass.class);

        DynamicClassConstructorBuilderContext<DynamicClassBuilderContext> firstLevelConstructorMethod = dynamicSubClassBuilderContext
                .addConstructor();

        firstLevelConstructorMethod.superParam(String.class);
        firstLevelConstructorMethod.superParam(String.class);

        DynamicProxyMethodBuilderContext firstLevelChangeAttribute1MethodBuildContext = dynamicSubClassBuilderContext.proxySuperMethod("changeAttribute1");
        firstLevelChangeAttribute1MethodBuildContext.visibility(MemberVisibility.PUBLIC);
        firstLevelChangeAttribute1MethodBuildContext.name("firstLevelChangeAttribute1");

        final Optional<Class<?>> optionalResult = dynamicSubClassBuilderContext.build( prvlookup );

        DynamicSubClassBuilderContext secondLevelDynamicSubClassBuilderContext = DynamicClassBuilder
                .createClass("org.divy.common.bo.dynamic.SecondLevelSubClass")
                .subClass(optionalResult.get());

        DynamicClassConstructorBuilderContext<DynamicClassBuilderContext> secondLevelConstructorMethod = secondLevelDynamicSubClassBuilderContext
                .addConstructor();

        secondLevelConstructorMethod.superParam(String.class);
        secondLevelConstructorMethod.superParam(String.class);

        DynamicProxyMethodBuilderContext secondLevelChangeAttribute1MethodBuildContext = secondLevelDynamicSubClassBuilderContext.proxySuperMethod("firstLevelChangeAttribute1");
        secondLevelChangeAttribute1MethodBuildContext.visibility(MemberVisibility.PUBLIC);
        secondLevelChangeAttribute1MethodBuildContext.name("secondLevelChangeAttribute1");

        DynamicProxyMethodBuilderContext secondLevelChangeAttribute2MethodBuildContext = secondLevelDynamicSubClassBuilderContext.proxySuperMethod("changeAttribute2");
        secondLevelChangeAttribute2MethodBuildContext.visibility(MemberVisibility.PUBLIC);
        secondLevelChangeAttribute2MethodBuildContext.name("secondLevelChangeAttribute2");

        final Optional<Class<?>> secondLevelOptionalResult = secondLevelDynamicSubClassBuilderContext.build( prvlookup );

        final Class<?> subClass = secondLevelOptionalResult.get();
        final Constructor<?> constructor = subClass.getConstructor(String.class, String.class);

        final BaseClass instance = (BaseClass)constructor.newInstance("attribute1", "attribute2");

        Method changeAttribute1Method = subClass.getMethod("secondLevelChangeAttribute1", String.class);
        changeAttribute1Method.invoke(instance,"changeFromSecondLevelClassAttribute1");

        Method changeAttribute2Method = subClass.getMethod("secondLevelChangeAttribute2", String.class);
        changeAttribute2Method.invoke(instance,"changeFromSecondLevelClassAttribute2");

        assertThat(instance, hasProperty("attribute1", is("changeFromSecondLevelClassAttribute1")));
        assertThat(instance, hasProperty("attribute2", is("changeFromSecondLevelClassAttribute2")));
    }
}
