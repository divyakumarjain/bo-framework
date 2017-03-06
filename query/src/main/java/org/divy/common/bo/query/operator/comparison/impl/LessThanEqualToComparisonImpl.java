package org.divy.common.bo.query.operator.comparison.impl;

import org.divy.common.bo.query.operator.comparison.LessThanEqualToComparison;


public class LessThanEqualToComparisonImpl<A> implements LessThanEqualToComparison<A> {
    private A value;

    public LessThanEqualToComparisonImpl() {
        super();
    }

    public LessThanEqualToComparisonImpl(A value) {
        super();
        this.value = value;
    }

    public void setValue(A value) {
        this.value = value;
    }

    @Override
    public A getValue() {
        return value;
    }
}
