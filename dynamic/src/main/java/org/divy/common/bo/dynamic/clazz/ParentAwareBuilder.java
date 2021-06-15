package org.divy.common.bo.dynamic.clazz;

public interface ParentAwareBuilder<P extends Builder> {
    P and();
}
