package org.divy.common.bo.query.operator.impl;

import org.divy.common.bo.query.operator.Operator;
import org.divy.common.bo.query.operator.Or;


public class OrImpl extends AbstractMultiOperator implements Or{

    public OrImpl(Operator... operators) {
        super(operators);
    }

}
