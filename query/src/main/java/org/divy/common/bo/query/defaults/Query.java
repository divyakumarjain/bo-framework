package org.divy.common.bo.query.defaults;

import org.divy.common.bo.query.Operator;

import java.util.HashMap;

public class Query extends HashMap<String, Operator> implements org.divy.common.bo.query.Query
{

    /**
     *
     */
    private static final long serialVersionUID = 6670043546158499600L;

    @Override
    public Operator get(String attributeName) {
        return super.get(attributeName);
    }

    @Override
    public Operator remove(String attributeName) {
        return super.remove(attributeName);
    }
}
