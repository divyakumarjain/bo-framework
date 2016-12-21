package org.divy.common.bo.query;


public interface Query
{
    static Query fromString(String value) {
        return new AttributeQuery();
    }
}
