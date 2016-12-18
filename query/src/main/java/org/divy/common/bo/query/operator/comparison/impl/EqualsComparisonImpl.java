package org.divy.common.bo.query.operator.comparison.impl;


public class EqualsComparisonImpl<ATTRIBUTE> implements org.divy.common.bo.query.operator.comparison.EqualsComparison<ATTRIBUTE> {

    private ATTRIBUTE value;

    public EqualsComparisonImpl() {
        super();
    }

    public EqualsComparisonImpl(ATTRIBUTE value) {
        super();
        this.value = value;
    }

    public void setValue(ATTRIBUTE value) {
        this.value = value;
    }

    @Override
    public ATTRIBUTE getValue() {
        return value;
    }

}
