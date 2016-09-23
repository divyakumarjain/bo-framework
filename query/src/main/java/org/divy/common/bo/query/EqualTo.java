package org.divy.common.bo.query;

@FunctionalInterface
public interface EqualTo<A> extends Comparison {
    A getValue();
}
