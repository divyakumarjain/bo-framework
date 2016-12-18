package org.divy.common.bo.query.operator.impl;

import org.divy.common.bo.query.operator.And;
import org.divy.common.bo.query.operator.Operator;

public class AndImpl extends AbstractMultiOperator implements And{
    public AndImpl(Operator... operators) {
        super(operators);
    }
}
