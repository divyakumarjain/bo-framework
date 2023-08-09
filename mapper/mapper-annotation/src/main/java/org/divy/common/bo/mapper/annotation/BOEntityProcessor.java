package org.divy.common.bo.mapper.annotation;

import com.google.auto.service.AutoService;
import org.divy.common.bo.mapper.annotation.generator.CustomMapperGenerator;
import org.divy.common.bo.mapper.annotation.generator.MapperGenerator;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.util.*;

@AutoService(Processor.class)
@SupportedAnnotationTypes("org.divy.common.bo.api.BOEntity")
public class BOEntityProcessor extends AbstractProcessor {
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_19;
    }
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        for (TypeElement annotation : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {

                ServiceLoader<MapperGenerator> loader = ServiceLoader.load(MapperGenerator.class);
                loader.stream().forEach(mapperGenerator -> {
                    var javaFiles = mapperGenerator.get().generate((TypeElement) element, processingEnv.getElementUtils().getPackageOf(element).getQualifiedName().toString());

                    javaFiles.forEach(file -> {
                        try {
                            file.writeTo(processingEnv.getFiler());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                });
            }
        }
        return true;
    }
}
