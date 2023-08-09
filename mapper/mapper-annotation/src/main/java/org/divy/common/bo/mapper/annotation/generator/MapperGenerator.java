package org.divy.common.bo.mapper.annotation.generator;

import com.squareup.javapoet.JavaFile;

import javax.lang.model.element.TypeElement;
import java.util.Set;

public interface MapperGenerator {
    public static final String SOURCE_PARAM = "source";
    public static final String TARGET_PARAM = "target";
    public static final String RETURN_TARGET = "return " + TARGET_PARAM;
    Set<JavaFile> generate(TypeElement element, String packageName);
}
