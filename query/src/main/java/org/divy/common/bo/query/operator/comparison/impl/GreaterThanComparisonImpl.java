package org.divy.common.bo.query.operator.comparison.impl;

import org.divy.common.bo.query.operator.comparison.GreaterThanComparison;


public class GreaterThanComparisonImpl<A> implements GreaterThanComparison<A> {

    private A value;

    public GreaterThanComparisonImpl() {
        super();
    }

    public GreaterThanComparisonImpl(A value) {
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
