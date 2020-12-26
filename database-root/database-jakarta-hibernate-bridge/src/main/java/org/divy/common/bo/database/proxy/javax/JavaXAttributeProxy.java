package org.divy.common.bo.database.proxy.javax;

import javax.persistence.metamodel.Attribute;

public class JavaXAttributeProxy<X,Y> implements Attribute<X,Y> {
    private final jakarta.persistence.metamodel.Attribute<X,Y> actual;

    public JavaXAttributeProxy( jakarta.persistence.metamodel.Attribute<X,Y> actual )
    {
        this.actual = actual;
    }
}
