package org.divy.common.bo.dynamic.test;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class HasMethodMatcherTest {

    @Test
    void testMethod() {
        assertThat(MockDynamicClass.class, hasMethod("noParameterMethod"));
    }
}