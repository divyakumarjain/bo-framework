package org.divy.common.bo.query.defaults;


public class LessThanComparison<A> implements org.divy.common.bo.query.LessThanComparison<A> {

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
