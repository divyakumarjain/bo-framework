package org.divy.common.bo.query.operator.comparison.impl;

import org.divy.common.bo.query.operator.comparison.LessThanComparison;


public class LessThanComparisonImpl<A> implements LessThanComparison<A> {

    private A value;

    public LessThanComparisonImpl() {
        super();
    }

    public LessThanComparisonImpl(A value) {
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
