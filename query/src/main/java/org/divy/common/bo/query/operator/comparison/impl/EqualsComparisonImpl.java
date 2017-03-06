package org.divy.common.bo.query.operator.comparison.impl;


public class EqualsComparisonImpl<A> implements org.divy.common.bo.query.operator.comparison.EqualsComparison<A> {

    private A value;

    public EqualsComparisonImpl() {
        super();
    }

    public EqualsComparisonImpl(A value) {
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
