package org.divy.common.bo;

import java.util.List;


public interface IBOManager<ENTITY extends IBusinessObject<ID>, ID>
{
	public ENTITY create(ENTITY businessObject);
	public ENTITY update(ENTITY businessObject);
	public ENTITY delete(ENTITY businessObject);

	public List<ENTITY> list();

	public List<ENTITY> search(IQuery<ENTITY> businessObjectQuery);

	public ENTITY deleteById(ID id);
	public ENTITY get(ID identity);
	
}
