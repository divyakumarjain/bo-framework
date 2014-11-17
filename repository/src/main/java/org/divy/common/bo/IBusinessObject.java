/**
 * 
 */
package org.divy.common.bo;

import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Divyakumar
 *
 */
public interface IBusinessObject<ID> {

    @XmlTransient
    ID identity();

    /**
     * update object with the copy
     * @param entity
     */
    void update(IBusinessObject<ID> entity);
}
