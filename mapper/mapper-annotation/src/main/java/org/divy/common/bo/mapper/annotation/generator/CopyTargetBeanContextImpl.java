package org.divy.common.bo.mapper.annotation.generator;

import com.squareup.javapoet.MethodSpec;

import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.List;

import static javax.lang.model.util.ElementFilter.fieldsIn;
import static org.divy.common.bo.mapper.annotation.generator.MapperGenerator.SOURCE_PARAM;
import static org.divy.common.bo.mapper.annotation.generator.MapperGenerator.TARGET_PARAM;

public class CopyTargetBeanContextImpl implements TargetBeanContext {
    @Override
    public TypeElement getTargetType(TypeElement typeElement) {
        return typeElement;
    }

    public List<VariableElement> getAllEnclosedFields( TypeElement element) {
        List<VariableElement> enclosedElements = new ArrayList<>();
        addEnclosedFieldsInHierarchy( enclosedElements, element, element );
        return enclosedElements;
    }

    private void addEnclosedFieldsInHierarchy( List<VariableElement> alreadyAdded,
                                               TypeElement element, TypeElement parentType) {


        addFields( alreadyAdded, fieldsIn( element.getEnclosedElements() ) );

        if ( hasNonObjectSuperclass( element ) ) {
            addEnclosedFieldsInHierarchy(
                    alreadyAdded,
                    asTypeElement( element.getSuperclass() ),
                    parentType
            );
        }
    }

    private boolean hasNonObjectSuperclass(TypeElement element) {

        return element.getSuperclass().getKind() == TypeKind.DECLARED
                && !asTypeElement( element.getSuperclass() ).getQualifiedName().toString().equals( "java.lang.Object" );
    }

    private static void addFields(List<VariableElement> alreadyCollected, List<VariableElement> variablesToAdd) {
        List<VariableElement> safeToAdd = new ArrayList<>( variablesToAdd.size() );
        safeToAdd.addAll( variablesToAdd );

        alreadyCollected.addAll( 0, safeToAdd );
    }

    private TypeElement asTypeElement(TypeMirror mirror) {
        return (TypeElement) ( (DeclaredType) mirror ).asElement();
    }

    @Override
    public void generateMapToBO(TypeElement element, MethodSpec.Builder maptoBoMethodBuilder) {
        buildCopyFieldsCode(element, maptoBoMethodBuilder);
    }

    private void buildCopyFieldsCode(TypeElement element, MethodSpec.Builder maptoBoMethodBuilder) {
        getAllEnclosedFields(element).forEach(field-> maptoBoMethodBuilder
                .addStatement(buildCopyFieldStatement(field)));
    }

    private static String buildCopyFieldStatement(VariableElement field) {
        return TARGET_PARAM + "." + resolveSetterMethod(field) + "(" + SOURCE_PARAM + "."+ resolveGetterMethod(field) + "())";
    }

    private static CharSequence resolveGetterMethod(VariableElement field) {
        return "get" + getFirstCapsSimpleName(field);
    }

    private static CharSequence getFirstCapsSimpleName(VariableElement field) {

        StringBuilder result = new StringBuilder();

        Name simpleName = field.getSimpleName();
        result.append(Character.toUpperCase(simpleName.charAt(0)));
        result.append(simpleName.subSequence(1,simpleName.length()));
        return result;
    }

    private static String resolveSetterMethod(VariableElement field) {
        return "set" + getFirstCapsSimpleName(field);
    }

    @Override
    public void generateMapFromBO(TypeElement element, MethodSpec.Builder mapFromBoMethodBuilder) {
        buildCopyFieldsCode(element, mapFromBoMethodBuilder);
    }
}
