package org.divy.common.bo.query;

@FunctionalInterface
public interface GreaterThanComparison<A> extends Comparison {
    A getValue();
}
