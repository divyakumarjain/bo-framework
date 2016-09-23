package org.divy.common.bo.query.defaults;


public class GreaterThanComparison<A> implements org.divy.common.bo.query.GreaterThanComparison<A> {

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
