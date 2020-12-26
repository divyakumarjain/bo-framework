package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.FetchParent;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.PluralAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import org.divy.common.bo.database.proxy.ProxyUtil;
import org.divy.common.bo.database.proxy.javax.JavaXPluralAttributeProxy;
import org.divy.common.bo.database.proxy.javax.JavaXSingularAttributeProxy;

import java.util.Set;

public class JakartaFetchProxy<Z, X> implements Fetch<Z, X> {
    private final javax.persistence.criteria.Fetch<Z, X> actual;

    public JakartaFetchProxy( javax.persistence.criteria.Fetch<Z, X> actual )
    {
        this.actual = actual;
    }

    @Override public Set<Fetch<X, ?>> getFetches()
    {
        return null;
    }

    @Override public <Y> Fetch<X, Y> fetch( SingularAttribute<? super X, Y> attribute )
    {
        return new JakartaFetchProxy<>( actual.fetch( new JavaXSingularAttributeProxy<>( attribute ) ) );
    }

    @Override public <Y> Fetch<X, Y> fetch( SingularAttribute<? super X, Y> attribute, JoinType jt )
    {
        return new JakartaFetchProxy<>( actual.fetch( new JavaXSingularAttributeProxy<>( attribute ), ProxyUtil.convertJoinType( jt) ) ) ;
    }

    @Override public <Y> Fetch<X, Y> fetch( PluralAttribute<? super X, ?, Y> attribute )
    {
        return new JakartaFetchProxy<>( actual.fetch( new JavaXPluralAttributeProxy<>( attribute ) ) );
    }

    @Override public <Y> Fetch<X, Y> fetch( PluralAttribute<? super X, ?, Y> attribute, JoinType jt )
    {
        return new JakartaFetchProxy<>( actual.fetch( new JavaXPluralAttributeProxy<>( attribute ), ProxyUtil.convertJoinType(jt ) ) );
    }

    @Override public <X1, Y> Fetch<X1, Y> fetch( String attributeName )
    {
        return new JakartaFetchProxy<>( actual.fetch( attributeName ) );
    }

    @Override public <X1, Y> Fetch<X1, Y> fetch( String attributeName, JoinType jt )
    {
        return new JakartaFetchProxy<>( actual.fetch( attributeName, ProxyUtil.convertJoinType( jt ) ) );
    }

    @Override public Attribute<? super Z, ?> getAttribute()
    {
        return new JakartaAttributeProxy<>( actual.getAttribute() );
    }

    @Override public FetchParent<?, Z> getParent()
    {
        return new JakartaFetchParentProxy<Object, Z>(  actual.getParent());
    }

    @Override public JoinType getJoinType()
    {
        return ProxyUtil.convertJoinType( actual.getJoinType());
    }
}
