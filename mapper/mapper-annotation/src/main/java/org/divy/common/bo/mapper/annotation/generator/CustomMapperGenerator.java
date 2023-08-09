package org.divy.common.bo.mapper.annotation.generator;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.*;
import org.divy.common.bo.mapper.BOMapper;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AutoService(MapperGenerator.class)
public class CustomMapperGenerator implements MapperGenerator {

    @Override
    public Set<JavaFile> generate(TypeElement element, String packageName) {

        var boEntityTypeSimpleName = element.getSimpleName();
        var className = boEntityTypeSimpleName.toString();

        return Stream.of(new CopyTargetBeanContextImpl()).map(targetBeanContext -> {
            var boEntityTypeName = TypeName.get(element.asType());
            var targetEntityTypeName = TypeName.get(targetBeanContext.getTargetType(element).asType());

            ParameterizedTypeName targetEntityCollectionTypeName = ParameterizedTypeName.get((ClassName) TypeName.get(Collection.class), targetEntityTypeName);
            ParameterizedTypeName boEntityCollectionTypeName = ParameterizedTypeName.get((ClassName) TypeName.get(Collection.class), boEntityTypeName);

            MethodSpec createFromBO = MethodSpec.methodBuilder("createFromBO")
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .addParameter(boEntityTypeName, SOURCE_PARAM)
                    .returns(targetEntityTypeName)
                    .addStatement("var " + TARGET_PARAM + " = new " + boEntityTypeSimpleName + "()")
                    .addStatement("return mapFromBO(" + SOURCE_PARAM + "," + TARGET_PARAM + ")")
                    .build();

            MethodSpec createBO = MethodSpec.methodBuilder("createBO")
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .addParameter(targetEntityTypeName, SOURCE_PARAM)
                    .returns(boEntityTypeName)
                    .addStatement("var " + TARGET_PARAM + " = new " + boEntityTypeSimpleName + "()")
                    .addStatement("return mapToBO(" + SOURCE_PARAM + "," + TARGET_PARAM + ")")
                    .build();


            MethodSpec mapToBO = MethodSpec.methodBuilder("mapToBO")
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .addParameter(targetEntityTypeName, SOURCE_PARAM)
                    .addParameter(boEntityTypeName, TARGET_PARAM)
                    .returns(boEntityTypeName)
                    .addStatement("doMapToBO(" + SOURCE_PARAM + "," + TARGET_PARAM + ")")
                    .addStatement(RETURN_TARGET)
                    .build();

            MethodSpec mapFromBO = MethodSpec.methodBuilder("mapFromBO")
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .addParameter(boEntityTypeName, SOURCE_PARAM)
                    .addParameter(targetEntityTypeName, TARGET_PARAM)
                    .addStatement("doMapFromBO(" + SOURCE_PARAM + "," + TARGET_PARAM + ")")
                    .returns(targetEntityTypeName)
                    .addStatement(RETURN_TARGET)
                    .build();


            MethodSpec createBOCollection = MethodSpec.methodBuilder("createBO")
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .addParameter(targetEntityCollectionTypeName, SOURCE_PARAM)
                    .returns(boEntityCollectionTypeName)
                    .addStatement("return " + SOURCE_PARAM + ".stream().map(this::createBO).collect(java.util.stream.Collectors.toList())")
                    .build();

            MethodSpec createFromBOCollection = MethodSpec.methodBuilder("createFromBO")
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .addParameter(boEntityCollectionTypeName, SOURCE_PARAM)
                    .returns(targetEntityCollectionTypeName)
                    .addStatement("return " + SOURCE_PARAM + ".stream().map(this::createFromBO).collect(java.util.stream.Collectors.toList())")
                    .build();



            MethodSpec.Builder mapFromBoMethodBuilder = MethodSpec.methodBuilder("doMapFromBO")
                    .addModifiers(Modifier.PRIVATE)
                    .addParameter(boEntityTypeName, SOURCE_PARAM)
                    .addParameter(targetEntityTypeName, TARGET_PARAM)
                    .returns(targetEntityTypeName);

            targetBeanContext.generateMapFromBO(element, mapFromBoMethodBuilder);

            mapFromBoMethodBuilder.addStatement(RETURN_TARGET);

            var mapFromBOMethod = mapFromBoMethodBuilder.build();

            MethodSpec.Builder maptoBoMethodBuilder = MethodSpec.methodBuilder("doMapToBO")
                    .addModifiers(Modifier.PRIVATE)
                    .addParameter(boEntityTypeName, SOURCE_PARAM)
                    .addParameter(targetEntityTypeName, TARGET_PARAM)
                    .returns(targetEntityTypeName);

            targetBeanContext.generateMapToBO(element, maptoBoMethodBuilder);

            maptoBoMethodBuilder.addStatement(RETURN_TARGET);

            var mapToBOMethod = maptoBoMethodBuilder.build();

            TypeSpec copierClass = TypeSpec.classBuilder(className + "Mapper")
                    .addSuperinterface(ParameterizedTypeName.get((ClassName) TypeName.get(BOMapper.class), boEntityTypeName, targetEntityTypeName))
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(createBO)
                    .addMethod(createFromBO)
                    .addMethod(createFromBOCollection)
                    .addMethod(createBOCollection)
                    .addMethod(mapFromBO)
                    .addMethod(mapToBO)
                    .addMethod(mapFromBOMethod)
                    .addMethod(mapToBOMethod)
                    .build();

            return JavaFile.builder(packageName, copierClass)
                    .build();
        }).collect(Collectors.toSet());


    }
}