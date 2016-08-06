package org.divy.common.bo.query.defaults;

import org.divy.common.bo.query.IEqualTo;


public class EqualTo<A> implements IEqualTo<A> {

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
