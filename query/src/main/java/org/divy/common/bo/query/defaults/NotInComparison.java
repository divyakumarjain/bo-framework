package org.divy.common.bo.query.defaults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotInComparison<A> implements org.divy.common.bo.query.NotInComparison<A> {

    private List<A> values;

    public NotInComparison(List<A> values) {
        super();
        this.values = values;
    }

    public NotInComparison(A[] values) {
        super();
        this.values = Arrays.asList(values);
    }

    public NotInComparison() {
        super();
    }

    @Override
    public List<A> getValues() {
        if (values == null)
            values = new ArrayList<A>();

        return values;
    }


}
