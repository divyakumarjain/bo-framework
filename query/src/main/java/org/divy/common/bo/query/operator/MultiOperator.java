package org.divy.common.bo.query.operator;

import java.util.List;

@FunctionalInterface
public interface MultiOperator<A> extends Operator{
    List<A> getOperations();
}
