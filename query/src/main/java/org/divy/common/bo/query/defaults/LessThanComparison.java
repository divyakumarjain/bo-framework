package org.divy.common.bo.query.defaults;

import org.divy.common.bo.query.ILessThanComparison;


public class LessThanComparison<A> implements ILessThanComparison<A> {

    private A value;

    public LessThanComparison() {
        super();
    }

    public LessThanComparison(A value) {
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
