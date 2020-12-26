package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Order;

public class JakartaOrderProxy implements Order {
    private final javax.persistence.criteria.Order actual;

    public JakartaOrderProxy( javax.persistence.criteria.Order actual )
    {
        this.actual = actual;
    }

    @Override public Order reverse()
    {
        return null;
    }

    @Override public boolean isAscending()
    {
        return false;
    }

    @Override public Expression<?> getExpression()
    {
        return null;
    }
}
