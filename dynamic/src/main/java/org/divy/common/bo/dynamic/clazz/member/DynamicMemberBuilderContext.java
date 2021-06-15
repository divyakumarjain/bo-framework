package org.divy.common.bo.dynamic.clazz.member;

import javassist.CtClass;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilderContext;
import org.divy.common.bo.dynamic.clazz.MemberVisibility;
import org.divy.common.bo.dynamic.clazz.common.DynamicAnnotatableBuilderContext;

import java.util.Optional;

public abstract class DynamicMemberBuilderContext<C extends DynamicMemberBuilderContext, P extends DynamicClassBuilderContext>
        extends DynamicAnnotatableBuilderContext <C, P> {

    protected String memberName = null;
    private MemberVisibility methodVisibility;
    private Class<?> memberType;

    protected DynamicMemberBuilderContext(P builderContext) {
        super(builderContext);
    }

    public DynamicMemberBuilderContext<C, P> visibility(MemberVisibility methodVisibility) {
        this.methodVisibility = methodVisibility;
        return this;
    }

    public DynamicMemberBuilderContext<C, P> name(String methodName) {
        this.memberName = methodName;
        return this;
    }

    public DynamicMemberBuilderContext<C, P> type(Class<?> memberType) {
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
