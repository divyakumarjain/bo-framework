package org.divy.common.bo;

import javax.xml.bind.annotation.XmlTransient;

public interface Identifiable<I> {
    @XmlTransient
    I identity();

    @XmlTransient
    String _type();

    @XmlTransient
    void _type(String _type);
}
