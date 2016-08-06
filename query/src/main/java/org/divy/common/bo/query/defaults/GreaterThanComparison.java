package org.divy.common.bo.query.defaults;

import org.divy.common.bo.query.IGreaterThanComparison;


public class GreaterThanComparison<A> implements IGreaterThanComparison<A> {

    private A value;

    public GreaterThanComparison() {
        super();
    }

    public GreaterThanComparison(A value) {
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
