package org.divy.common.bo.query;

@FunctionalInterface
public interface LessThanComparison<A> extends Comparison {
    A getValue();
}
