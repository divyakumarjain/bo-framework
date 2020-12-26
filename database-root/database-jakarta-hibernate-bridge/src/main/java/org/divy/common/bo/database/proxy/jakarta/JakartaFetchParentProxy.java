package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.FetchParent;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.metamodel.PluralAttribute;
import jakarta.persistence.metamodel.SingularAttribute;

import java.util.Set;

public class JakartaFetchParentProxy<Z, X> implements FetchParent<Z, X> {
    private final javax.persistence.criteria.FetchParent<Z, X> actual;

    public JakartaFetchParentProxy( javax.persistence.criteria.FetchParent<Z, X> actual )
    {
        this.actual = actual;
    }

    @Override public Set<Fetch<X, ?>> getFetches()
    {
        return null;
    }

    @Override public <Y> Fetch<X, Y> fetch( SingularAttribute<? super X, Y> attribute )
    {
        return null;
    }

    @Override public <Y> Fetch<X, Y> fetch( SingularAttribute<? super X, Y> attribute, JoinType jt )
    {
        return null;
    }

    @Override public <Y> Fetch<X, Y> fetch( PluralAttribute<? super X, ?, Y> attribute )
    {
        return null;
    }

    @Override public <Y> Fetch<X, Y> fetch( PluralAttribute<? super X, ?, Y> attribute, JoinType jt )
    {
        return null;
    }

    @Override public <X1, Y> Fetch<X1, Y> fetch( String attributeName )
    {
        return null;
    }

    @Override public <X1, Y> Fetch<X1, Y> fetch( String attributeName, JoinType jt )
    {
        return null;
    }
}
