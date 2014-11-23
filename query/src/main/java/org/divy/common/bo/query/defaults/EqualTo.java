package org.divy.common.bo.query.defaults;

import org.divy.common.bo.query.IEqualTo;


public class EqualTo<ATTRIBUTE> implements IEqualTo<ATTRIBUTE> {

    private ATTRIBUTE value;

    public EqualTo() {
        super();
    }

    public EqualTo(ATTRIBUTE value) {
        super();
        this.value = value;
    }

    public void setValue(ATTRIBUTE value) {
        this.value = value;
    }

    @Override
    public ATTRIBUTE getValue() {
        return value;
    }

}
