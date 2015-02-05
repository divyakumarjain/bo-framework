package org.divy.common.bo.query.defaults;

import org.divy.common.bo.query.IOperator;
import org.divy.common.bo.query.IQuery;

import java.util.HashMap;

public class Query extends HashMap<String, IOperator> implements IQuery
{

    /**
     *
     */
    private static final long serialVersionUID = 6670043546158499600L;

    @Override
    public IOperator get(String attributeName) {
        return super.get(attributeName);
    }

    @Override
    public IOperator remove(String attributeName) {
        return super.remove(attributeName);
    }
}
