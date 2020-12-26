package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.criteria.Selection;
import org.divy.common.bo.database.proxy.ProxyUtil;

import java.util.List;

public class JakartaSelectionProxy<T> implements Selection<T> {
    private final javax.persistence.criteria.Selection<T> actual;

    public JakartaSelectionProxy( javax.persistence.criteria.Selection<T> actual )
    {
        this.actual = actual;
    }

    @Override public Selection<T> alias( String name )
    {
        return new JakartaSelectionProxy<>( actual.alias( name ) );
    }

    @Override public boolean isCompoundSelection()
    {
        return actual.isCompoundSelection();
    }

    @Override public List<Selection<?>> getCompoundSelectionItems()
    {
        return ProxyUtil.convertListProxy( actual.getCompoundSelectionItems(), JakartaSelectionProxy::new );
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
