/**
 * 
 */
package org.divy.common.bo;

import javax.xml.bind.annotation.XmlTransient;

/**
 * @author divyakumar.a.jain@hp.com
 *
 */
public interface IBusinessObject<ID> {

	@XmlTransient
	ID identity();
}
