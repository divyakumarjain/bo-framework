package org.divy.common.bo.query;

@FunctionalInterface
public interface IEqualTo<A> extends IComparison {
    A getValue();
}
