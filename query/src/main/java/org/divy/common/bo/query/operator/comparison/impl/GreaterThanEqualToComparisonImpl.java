package org.divy.common.bo.query.operator.comparison.impl;

import org.divy.common.bo.query.operator.comparison.GreaterThanEqualToComparison;


public class GreaterThanEqualToComparisonImpl<ATTRIBUTE> implements
        GreaterThanEqualToComparison<ATTRIBUTE> {

    private ATTRIBUTE value;

    public GreaterThanEqualToComparisonImpl() {
        super();
    }

    public GreaterThanEqualToComparisonImpl(ATTRIBUTE value) {
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
