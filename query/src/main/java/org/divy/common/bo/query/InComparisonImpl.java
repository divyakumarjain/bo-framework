package org.divy.common.bo.query;

import org.divy.common.bo.query.operator.comparison.InComparison;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InComparisonImpl<A> implements InComparison<A> {

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
        if (values == null)
            values = new ArrayList<>();

        return values;
    }

}
