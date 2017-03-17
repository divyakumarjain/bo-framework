package org.divy.common.bo.dynamic.clazz.member;

import javassist.CtClass;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilderContext;
import org.divy.common.bo.dynamic.clazz.MemberVisibility;
import org.divy.common.bo.dynamic.clazz.common.DynamicAnnotatableBuilderContext;

import java.util.Optional;

public abstract class DynamicMemberBuilderContext<T extends DynamicMemberBuilderContext>
        extends DynamicAnnotatableBuilderContext <T, DynamicClassBuilderContext> {

    private String memberName = null;
    private MemberVisibility methodVisibility;
    private Class<?> memberType;

    public DynamicMemberBuilderContext(DynamicClassBuilderContext builderContext) {
        super(builderContext);
    }

    public DynamicMemberBuilderContext visibility(MemberVisibility methodVisibility) {
        this.methodVisibility = methodVisibility;
        return this;
    }

    public DynamicMemberBuilderContext name(String methodName) {
        this.memberName = methodName;
        return this;
    }

    public DynamicMemberBuilderContext type(Class<?> memberType) {
        this.memberType = memberType;
        return this;
    }

    protected String getMemberName() {
        return memberName;
    }

    MemberVisibility getMethodVisibility() {
        return methodVisibility;
    }

    protected Optional<CtClass> getMemberType() {
        return getCtClass(memberType);
    }

}
