package org.divy.common.bo.query.operator.comparison;

import org.divy.common.bo.query.operator.Operator;

import java.util.List;

@FunctionalInterface
public interface InComparison<A> extends Operator{
    List<A> getValues();
}