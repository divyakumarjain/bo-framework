package org.divy.common.bo.dynamic.clazz.common;

import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.EnumMemberValue;
import javassist.bytecode.annotation.MemberValue;

public class EnumAnnotationParam implements DynamicAnnotationParam {
    private final Enum value;

    public EnumAnnotationParam(Enum value) {
        this.value = value;
    }

    @Override
    public MemberValue doBuildAnnotationParamValue(ConstPool constPool) {
        EnumMemberValue enumMemberValue = new EnumMemberValue(constPool);
        enumMemberValue.setType(value.getClass().getTypeName());
        enumMemberValue.setValue(value.name());
        return enumMemberValue;
    }
}
