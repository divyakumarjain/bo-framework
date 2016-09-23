package org.divy.common.bo.query.defaults;


public class NotEqualToComparison<A> implements org.divy.common.bo.query.NotEqualToComparison<A> {

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
