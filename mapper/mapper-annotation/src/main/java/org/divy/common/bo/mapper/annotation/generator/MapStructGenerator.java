package org.divy.common.bo.mapper.annotation.generator;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.*;
import org.divy.common.bo.mapper.BOMapper;
import org.divy.common.bo.mapper.annotation.FromEntity;
import org.divy.common.bo.mapper.annotation.ToEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.util.Collection;
import java.util.Set;

@AutoService(MapperGenerator.class)
public class MapStructGenerator implements MapperGenerator {
    @Override
    public Set<JavaFile> generate(TypeElement element, String packageName) {
        var boEntityTypeSimpleName = element.getSimpleName();
        var className = boEntityTypeSimpleName.toString();

        var boEntityTypeName = TypeName.get(element.asType());

        ParameterizedTypeName targetEntityCollectionTypeName = ParameterizedTypeName.get((ClassName) TypeName.get(Collection.class), boEntityTypeName);
        ParameterizedTypeName boEntityCollectionTypeName = ParameterizedTypeName.get((ClassName) TypeName.get(Collection.class), boEntityTypeName);

        MethodSpec createFromBO = MethodSpec.methodBuilder("createFromBO")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(boEntityTypeName, SOURCE_PARAM)
                .returns(boEntityTypeName)
                .addStatement("var " + TARGET_PARAM + " = new " + boEntityTypeSimpleName + "()")
                .addStatement("return mapFromBO(" + SOURCE_PARAM + "," + TARGET_PARAM + ")")
                .build();

        MethodSpec createBO = MethodSpec.methodBuilder("createBO")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(boEntityTypeName, SOURCE_PARAM)
                .returns(boEntityTypeName)
                .addStatement("var " + TARGET_PARAM + " = new " + boEntityTypeSimpleName + "()")
                .addStatement("return mapToBO(" + SOURCE_PARAM + "," + TARGET_PARAM + ")")
                .build();


        MethodSpec mapToBO = MethodSpec.methodBuilder("mapToBO")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(boEntityTypeName, SOURCE_PARAM)
                .addParameter(boEntityTypeName, TARGET_PARAM)
                .returns(boEntityTypeName)
                .addStatement("doMapToBO(" + SOURCE_PARAM + "," + TARGET_PARAM + ")")
                .addStatement(RETURN_TARGET)
                .build();

        MethodSpec mapFromBO = MethodSpec.methodBuilder("mapFromBO")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(boEntityTypeName, SOURCE_PARAM)
                .addParameter(boEntityTypeName, TARGET_PARAM)
                .addStatement("doMapFromBO(" + SOURCE_PARAM + "," + TARGET_PARAM + ")")
                .returns(boEntityTypeName)
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

        MethodSpec mapFromBOMethod = MethodSpec.methodBuilder("doMapFromBO")
                .addModifiers(Modifier.PROTECTED, Modifier.ABSTRACT)
                .addAnnotation(FromEntity.class)
                .addAnnotation(AnnotationSpec.builder(Mapping.class).addMember("target", "\".\"").addMember("source", "\"source\"").build())
                .addParameter(boEntityTypeName, SOURCE_PARAM)
                .addParameter(ParameterSpec.builder(boEntityTypeName, TARGET_PARAM).addAnnotation(AnnotationSpec.builder(MappingTarget.class).build()).build())
                .returns(boEntityTypeName)
                .build();

        MethodSpec mapToBOMethod = MethodSpec.methodBuilder("doMapToBO")
                .addModifiers(Modifier.PROTECTED, Modifier.ABSTRACT)
                .addAnnotation(ToEntity.class)
                .addAnnotation(AnnotationSpec.builder(Mapping.class).addMember("target", "\".\"").addMember("source", "\"source\"").build())
                .addParameter(boEntityTypeName, SOURCE_PARAM)
                .addParameter(ParameterSpec.builder(boEntityTypeName, TARGET_PARAM).addAnnotation(AnnotationSpec.builder(MappingTarget.class).build()).build())
                .returns(boEntityTypeName)
                .build();

        TypeSpec copierClass = TypeSpec.classBuilder(className + "MapStructMapper")
                .addSuperinterface(ParameterizedTypeName.get((ClassName) TypeName.get(BOMapper.class), boEntityTypeName, boEntityTypeName))
                .addAnnotation(Mapper.class)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .addMethod(createBO)
                .addMethod(createFromBO)
                .addMethod(createFromBOCollection)
                .addMethod(createBOCollection)
                .addMethod(mapFromBO)
                .addMethod(mapToBO)
                .addMethod(mapFromBOMethod)
                .addMethod(mapToBOMethod)
                .build();

        JavaFile build = JavaFile.builder(packageName, copierClass)
                .build();
        return Set.of(build);
    }
}
