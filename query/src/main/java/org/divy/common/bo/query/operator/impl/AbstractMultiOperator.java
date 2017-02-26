package org.divy.common.bo.query.operator.impl;

import org.divy.common.bo.query.operator.MultiOperator;
import org.divy.common.bo.query.operator.Operator;

import java.util.Arrays;
import java.util.List;

public class AbstractMultiOperator implements MultiOperator<Operator>{
    final List<Operator> operators;

    public AbstractMultiOperator(Operator... operators) {
        this.operators = Arrays.asList(operators);
    }

    public List<Operator> getOperations() {
        return operators;
    }
}
