package org.divy.common.bo.query.operator.comparison.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InComparisonImpl<A> implements org.divy.common.bo.query.operator.comparison.InComparison<A> {

    private List<A> values;

    public InComparisonImpl(List<A> values) {
        super();
        this.values = values;
    }

    public InComparisonImpl(A[] values) {
        super();
        this.values = Arrays.asList(values);
    }

    public InComparisonImpl() {
        super();
    }

    @Override
    public List<A> getValues() {
        if(values==null)
            values = new ArrayList<>();

        return values;
    }

}
