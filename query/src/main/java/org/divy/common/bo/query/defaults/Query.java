package org.divy.common.bo.query.defaults;

import java.util.HashMap;
import java.util.Set;

import org.divy.common.bo.query.IOperator;
import org.divy.common.bo.query.IQuery;

public class Query extends HashMap<String, IOperator> implements IQuery
{

    /**
     *
     */
    private static final long serialVersionUID = 6670043546158499600L;

    @Override
    public Object get(String attributeName) {
        return super.get(attributeName);
    }

    @Override
    public Object remove(String attributeName) {
        return super.remove(attributeName);
    }

    @Override
    public Set<java.util.Map.Entry<String,IOperator>> getAll() {
        Set<java.util.Map.Entry<String, IOperator>> entrySet = super.entrySet();
        return entrySet;
    }

}
