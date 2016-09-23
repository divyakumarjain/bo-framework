package org.divy.common.bo.query.defaults;


public class GreaterThanEqualToComparison<A> implements
        org.divy.common.bo.query.GreaterThanEqualToComparison<A> {

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
