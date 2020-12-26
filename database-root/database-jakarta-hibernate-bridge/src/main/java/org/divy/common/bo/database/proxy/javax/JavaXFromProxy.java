package org.divy.common.bo.database.proxy.javax;

import javax.persistence.criteria.From;

public class JavaXFromProxy <X,Y> implements From<X, Y> {
    private final jakarta.persistence.criteria.From<X, Y> actual;

    public JavaXFromProxy( jakarta.persistence.criteria.From<X, Y> actual )
    {
        this.actual = actual;
    }
}
