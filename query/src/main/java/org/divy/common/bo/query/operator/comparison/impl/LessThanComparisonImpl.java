package org.divy.common.bo.query.operator.comparison.impl;

import org.divy.common.bo.query.operator.comparison.LessThanComparison;


public class LessThanComparisonImpl<ATTRIBUTE> implements LessThanComparison<ATTRIBUTE> {

    private ATTRIBUTE value;

    public LessThanComparisonImpl() {
        super();
    }

    public LessThanComparisonImpl(ATTRIBUTE value) {
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
