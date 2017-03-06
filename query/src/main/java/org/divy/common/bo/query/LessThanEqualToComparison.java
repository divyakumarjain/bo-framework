package org.divy.common.bo.query;

@FunctionalInterface
public interface LessThanEqualToComparison<A> extends Comparison {
    A getValue();
}
