package org.divy.common.bo.query;

@FunctionalInterface
public interface NotEqualToComparison<A> extends Comparison {
    A getValue();
}
