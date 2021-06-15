package org.divy.common.bo.query.operator.comparison;

import org.divy.common.bo.query.operator.And;
import org.divy.common.bo.query.operator.Not;
import org.divy.common.bo.query.operator.Operator;
import org.divy.common.bo.query.operator.Or;
import org.divy.common.bo.query.operator.comparison.impl.*;
import org.divy.common.bo.query.operator.impl.AndImpl;
import org.divy.common.bo.query.operator.impl.NotImpl;
import org.divy.common.bo.query.operator.impl.OrImpl;

public class OperatorFactory
{
    private OperatorFactory() {
        //NOOP
    }

    public static <A> EqualsComparison<A> equalsComparison(A value) {
        return new EqualsComparisonImpl<>(value);
    }

    public static <A> GreaterThanComparison<A> greaterThanComparison(A value)
    {
        return new GreaterThanComparisonImpl<>(value);
    }

    public static <A> GreaterThanEqualToComparison<A> greaterThanEqualToComparison(A value)
    {
        return new GreaterThanEqualToComparisonImpl<>(value);
    }

    public static <A> LessThanComparison<A> lessThanComparison(A value)
    {
        return new LessThanComparisonImpl<>(value);
    }

    public static <A> LessThanEqualToComparison<A> lessThanEqualToComparison(A value)
    {
        return new LessThanEqualToComparisonImpl<>(value);
    }

    public static <A extends Operator> Not not(A operator)
    {
        return new NotImpl(operator);
    }

    public static <A extends Operator, B extends Operator> And and(A a, B b)
    {
        return new AndImpl(a,b);
    }

    public static <A extends Operator, B extends Operator> Or or(A a, B b)
    {
        return new OrImpl(a,b);
    }
}
