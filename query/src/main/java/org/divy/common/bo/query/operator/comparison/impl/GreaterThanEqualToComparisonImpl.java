package org.divy.common.bo.query.operator.comparison.impl;

import org.divy.common.bo.query.operator.comparison.GreaterThanEqualToComparison;


public class GreaterThanEqualToComparisonImpl<A> implements
        GreaterThanEqualToComparison<A> {

    private A value;

    public GreaterThanEqualToComparisonImpl() {
        super();
    }

    public GreaterThanEqualToComparisonImpl(A value) {
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
