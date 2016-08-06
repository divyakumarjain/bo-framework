package org.divy.common.bo.query.defaults;

import org.divy.common.bo.query.INotEqualToComparison;


public class NotEqualToComparison<A> implements INotEqualToComparison<A> {

    private A value;

    public NotEqualToComparison() {
        super();
    }

    public NotEqualToComparison(A value) {
        super();
        this.value = value;
    }

    @Override
    public A getValue() {
        return value;
    }

    public void setValue(A value) {
        this.value = value;
    }
}
