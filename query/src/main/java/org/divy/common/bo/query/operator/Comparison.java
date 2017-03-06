package org.divy.common.bo.query.operator;

@FunctionalInterface
public interface Comparison<A> extends Operator {
    A getValue();
}
