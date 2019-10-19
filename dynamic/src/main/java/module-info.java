module bo.framework.dynamic {
    requires javassist;
    requires slf4j.api;
    requires bo.framework.exception.handling;
    requires commons.beanutils;
    requires net.bytebuddy;
    exports org.divy.common.bo.dynamic.clazz;
    exports org.divy.common.bo.dynamic.clazz.member;
    exports org.divy.common.bo.dynamic.clazz.common;
    exports org.divy.common.bo.dynamic.clazz.member.method;
    exports org.divy.common.bo.dynamic.clazz.member.constructor;
    exports org.divy.common.bo.dynamic.clazz.member.method.reader;
    opens java.base;
}
