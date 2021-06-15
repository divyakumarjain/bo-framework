package org.divy.common.bo.dynamic.clazz.common;

import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.EnumMemberValue;
import javassist.bytecode.annotation.MemberValue;

import java.util.Arrays;

public class EnumArrayAnnotationParam implements DynamicAnnotationParam {

    private final Enum[] values;

    public EnumArrayAnnotationParam(Enum value) {
        values = new Enum[] {value};
    }

    public EnumArrayAnnotationParam(Enum[] values) {
        this.values = values;
    }

    @Override
    public MemberValue doBuildAnnotationParamValue(ConstPool constPool) {
        EnumMemberValue[] memberValues = Arrays.stream(values)
                .map(param -> {
                    var enumMemberValue = new EnumMemberValue(constPool);
                    enumMemberValue.setType(param.getClass().getTypeName());
                    enumMemberValue.setValue(param.name());
                    return enumMemberValue;
                })
                .toArray(EnumMemberValue[]::new);
        var arrayMemberValue = new ArrayMemberValue(constPool);
        arrayMemberValue.setValue(memberValues);
        return arrayMemberValue;
    }
}
