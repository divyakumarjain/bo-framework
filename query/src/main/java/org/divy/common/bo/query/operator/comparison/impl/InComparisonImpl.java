package org.divy.common.bo.query.operator.comparison.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InComparisonImpl<ATTRIBUTE> implements org.divy.common.bo.query.operator.comparison.InComparison<ATTRIBUTE> {

    private List<ATTRIBUTE> values;

    @Override
    public List<ATTRIBUTE> getValues() {
        if(values==null)
            values = new ArrayList<>();

        return values;
    }

    public InComparisonImpl(List<ATTRIBUTE> values) {
        super();
        this.values = values;
    }

    public InComparisonImpl(ATTRIBUTE[] values) {
        super();
        this.values = Arrays.asList(values);
    }

    public InComparisonImpl() {
        super();
    }

}
