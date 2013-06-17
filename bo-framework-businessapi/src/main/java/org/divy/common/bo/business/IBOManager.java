package org.divy.common.bo.business;

import java.util.List;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.IQuery;

public interface IBOManager<ENTITY extends IBusinessObject<ID>, ID> {

	ENTITY create(ENTITY businessObject);

	ENTITY update(ENTITY businessObject);

	ENTITY delete(ENTITY businessObject);

	List<ENTITY> list();

	List<ENTITY> search(IQuery<ENTITY> businessObjectQuery);

	ENTITY deleteById(ID id);

	ENTITY get(ID identity);
}
