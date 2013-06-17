package org.divy.common.bo;

import java.util.List;


public interface IBORepository<ENTITY extends IBusinessObject<ID>, ID>
{
	ENTITY create(ENTITY businessObject);

	ENTITY update(ENTITY businessObject);

	ENTITY delete(ENTITY businessObject);

	List<ENTITY> list();

	List<ENTITY> search(IQuery<ENTITY> businessObjectQuery);

	ENTITY deleteById(ID id);

	ENTITY get(ID identity);
	
}
