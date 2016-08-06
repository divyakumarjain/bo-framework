package org.divy.common.bo.query.defaults;

import org.divy.common.bo.query.IGreaterThanEqualToComparison;


public class GreaterThanEqualToComparison<A> implements
        IGreaterThanEqualToComparison<A> {

    private A value;

    public GreaterThanEqualToComparison() {
        super();
    }

    public GreaterThanEqualToComparison(A value) {
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
