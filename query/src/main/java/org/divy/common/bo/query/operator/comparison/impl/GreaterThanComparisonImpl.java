package org.divy.common.bo.query.operator.comparison.impl;

import org.divy.common.bo.query.operator.comparison.GreaterThanComparison;


public class GreaterThanComparisonImpl<ATTRIBUTE> implements GreaterThanComparison<ATTRIBUTE> {

    private ATTRIBUTE value;

    public GreaterThanComparisonImpl() {
        super();
    }

    public GreaterThanComparisonImpl(ATTRIBUTE value) {
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
