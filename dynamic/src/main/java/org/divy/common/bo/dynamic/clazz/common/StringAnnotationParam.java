package org.divy.common.bo.dynamic.clazz.common;

import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.StringMemberValue;

public class StringAnnotationParam implements DynamicAnnotationParam{
    private String value;

    public StringAnnotationParam(String value) {
        this.value = value;
    }

    @Override
    public MemberValue doBuildAnnotationParamValue(ConstPool constPool) {
        return new StringMemberValue(value, constPool);
    }
}
