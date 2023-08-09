package org.divy.common.bo.mapper.annotation.generator;

import com.squareup.javapoet.MethodSpec;

import javax.lang.model.element.TypeElement;

public interface TargetBeanContext {
    TypeElement getTargetType(TypeElement typeName);

    void generateMapFromBO(TypeElement element, MethodSpec.Builder maptoBoMethodBuilder);

    void generateMapToBO(TypeElement element, MethodSpec.Builder maptoBoMethodBuilder);
}
