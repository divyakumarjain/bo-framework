package org.divy.common.bo;

import javax.xml.bind.annotation.XmlTransient;

@FunctionalInterface
public interface IBusinessObject<I> {

    @XmlTransient
    I identity();
}
