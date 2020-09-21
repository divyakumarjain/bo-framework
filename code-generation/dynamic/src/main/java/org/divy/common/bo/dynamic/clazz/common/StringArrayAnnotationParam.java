package org.divy.common.bo.dynamic.clazz.common;

import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.StringMemberValue;

import java.util.Arrays;

public class StringArrayAnnotationParam implements DynamicAnnotationParam {
    private String[] values;

    public StringArrayAnnotationParam(String[] values) {
        this.values = values;
    }

    public StringArrayAnnotationParam(String value) {
        this.values = new String[] {value};
    }

    @Override
    public MemberValue doBuildAnnotationParamValue(ConstPool constPool) {
        StringMemberValue[] memberValues = Arrays.stream(values)
                .map(param -> new StringMemberValue(param, constPool))
                .toArray(StringMemberValue[]::new);
        ArrayMemberValue arrayMemberValue = new ArrayMemberValue(constPool);
        arrayMemberValue.setValue(memberValues);
        return arrayMemberValue;
    }
}
