package org.divy.common.bo;

import javax.xml.bind.annotation.XmlTransient;

/**
 *
 *
 */
public interface IBusinessObject<I> {

    @XmlTransient
    I identity();

    /**
     * update object with the copy
     * @param entity
     */
    void update(IBusinessObject<I> entity);
}
