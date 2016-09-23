package org.divy.common.bo.query.defaults;


public class EqualTo<A> implements org.divy.common.bo.query.EqualTo<A> {

    private A value;

    public EqualTo() {
        super();
    }

    public EqualTo(A value) {
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
