package org.divy.common.bo.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InComparison<A> implements IInComparison<A> {

    private List<A> values;

    public InComparison(List<A> values) {
        super();
        this.values = values;
    }

    public InComparison(A[] values) {
        super();
        this.values = Arrays.asList(values);
    }

    public InComparison() {
        super();
    }

    @Override
    public List<A> getValues() {
        if (values == null)
            values = new ArrayList<A>();

        return values;
    }

}
