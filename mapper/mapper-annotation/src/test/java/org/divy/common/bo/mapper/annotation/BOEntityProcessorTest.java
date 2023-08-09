package org.divy.common.bo.mapper.annotation;

import com.google.common.io.Resources;
import com.google.common.truth.StringSubject;
import com.google.testing.compile.Compilation;
import com.google.testing.compile.CompilationSubject;
import com.google.testing.compile.Compiler;
import com.google.testing.compile.JavaFileObjects;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import javax.tools.StandardLocation;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class BOEntityProcessorTest {

//    BOMapper<Source, Target> mapperUnderTest;
    @Test
    void testCreateFromBo_success() throws IOException {
        Compilation compilation =
                Compiler.javac()
                        .withOptions("-implicit:none")
                        .withProcessors(new BOEntityProcessor(), new org.mapstruct.ap.MappingProcessor())
                        .compile(
                                JavaFileObjects.forResource("SourceBOEntity.java"));
        CompilationSubject.assertThat(compilation).succeededWithoutWarnings();

        var sourceBOEntityMapperPlannedCode = Resources
                .asCharSource(Resources.getResource("SourceBOEntityMapper.java"), Charset.defaultCharset())
                .read();

        CompilationSubject.assertThat(compilation)
                .generatedFile(StandardLocation.SOURCE_OUTPUT, "SourceBOEntityMapper.java")
                .contentsAsString(Charset.defaultCharset()).isEqualTo(sourceBOEntityMapperPlannedCode);


//        assertThat(sourceBOEntityMapperActualCode, equalToCompressingWhiteSpace(sourceBOEntityMapperPlannedCode));

        byte[] actualSource = compilation.generatedFile(StandardLocation.SOURCE_OUTPUT,"SourceBOEntityMapStructMapper.java").get().openInputStream().readAllBytes();
        byte[] expectedSource = Resources.asByteSource(Resources.getResource("SourceBOEntityMapStructMapper.java")).read();

        assertThat(new String(actualSource), IsEqual.equalTo(new String(expectedSource)));
    }
}