package org.divy.common.bo.query.operator;

@FunctionalInterface
public interface Not extends Operator{
    Operator getOperation();
}
