package org.divy.common.bo.database.proxy.javax;

import jakarta.persistence.criteria.Selection;
import org.divy.common.bo.database.proxy.ProxyUtil;

import java.util.List;

public class JavaXSelectionProxy<T> implements javax.persistence.criteria.Selection<T> {
    private final Selection<T> actual;

    public JavaXSelectionProxy( Selection<T> actual )
    {
        this.actual = actual;
    }

    @Override public javax.persistence.criteria.Selection<T> alias( String s )
    {
        return new JavaXSelectionProxy<>( actual.alias( s ));
    }

    @Override public boolean isCompoundSelection()
    {
        return actual.isCompoundSelection();
    }

    @Override public List<javax.persistence.criteria.Selection<?>> getCompoundSelectionItems()
    {
        return ProxyUtil.convertListProxy(  actual.getCompoundSelectionItems(), JavaXSelectionProxy::new );
    }

    @Override public Class<? extends T> getJavaType()
    {
        return actual.getJavaType();
    }

    @Override public String getAlias()
    {
        return actual.getAlias();
    }
}
