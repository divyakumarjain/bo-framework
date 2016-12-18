package org.divy.common.bo.query.operator.comparison.impl;

import org.divy.common.bo.query.operator.comparison.LessThanEqualToComparison;

/**
 * Created by divyjain on 12/6/2016.
 */
public class LessThanEqualToComparisonImpl<ATTRIBUTE> implements LessThanEqualToComparison<ATTRIBUTE> {
    private ATTRIBUTE value;

    public LessThanEqualToComparisonImpl() {
        super();
    }

    public LessThanEqualToComparisonImpl(ATTRIBUTE value) {
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
