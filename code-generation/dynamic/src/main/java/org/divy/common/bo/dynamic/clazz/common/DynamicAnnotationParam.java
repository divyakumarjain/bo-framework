package org.divy.common.bo.dynamic.clazz.common;

import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.MemberValue;

public interface DynamicAnnotationParam {
    MemberValue doBuildAnnotationParamValue(ConstPool constPool);
}
