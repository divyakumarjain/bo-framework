package org.divy.common.bo.query.operator.impl;

import org.divy.common.bo.query.operator.Not;
import org.divy.common.bo.query.operator.Operator;

public class NotImpl implements Not{

    private Operator operator;

    public NotImpl(Operator operator) {
        this.operator = operator;
    }

    @Override
    public Operator getOperation() {
        return operator;
    }
}
