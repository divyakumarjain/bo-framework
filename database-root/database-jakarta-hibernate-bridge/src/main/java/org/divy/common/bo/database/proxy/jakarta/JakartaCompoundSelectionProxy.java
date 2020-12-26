package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.criteria.CompoundSelection;
import jakarta.persistence.criteria.Selection;
import org.divy.common.bo.database.proxy.ProxyUtil;

import java.util.List;

public class JakartaCompoundSelectionProxy<S> implements CompoundSelection<S> {
    private final javax.persistence.criteria.CompoundSelection<S> actual;

    public JakartaCompoundSelectionProxy( javax.persistence.criteria.CompoundSelection<S> actual )
    {
        this.actual = actual;
    }

    @Override public Selection<S> alias( String name )
    {
        return new JakartaSelectionProxy<>( actual.alias( name ) );
    }

    @Override public boolean isCompoundSelection()
    {
        return false;
    }

    @Override public List<Selection<?>> getCompoundSelectionItems()
    {
        return ProxyUtil.convertListProxy( actual.getCompoundSelectionItems(), JakartaSelectionProxy::new );
    }

    @Override public Class<? extends S> getJavaType()
    {
        return actual.getJavaType();
    }

    @Override public String getAlias()
    {
        return actual.getAlias();
    }
}
