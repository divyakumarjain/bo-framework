package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.criteria.Order;
import org.divy.common.bo.database.proxy.javax.JavaXExpressionProxy;

import javax.persistence.criteria.Expression;

public class JavaXOrder implements javax.persistence.criteria.Order{
    private final Order actual;

    public JavaXOrder( Order actual) {
        this.actual = actual;
    }

    @Override public javax.persistence.criteria.Order reverse()
    {
        return new JavaXOrder( actual.reverse() );
    }

    @Override public boolean isAscending()
    {
        return actual.isAscending();
    }

    @Override public Expression<?> getExpression()
    {
        return new JavaXExpressionProxy<>( actual.getExpression() );
    }
}
