package org.divy.common.bo.business;

import java.util.List;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.query.IQuery;

public interface IBOManager<ENTITY extends IBusinessObject<ID>, ID> {

    ENTITY create(ENTITY businessObject);

    ENTITY update(ENTITY businessObject);

    ENTITY delete(ENTITY businessObject);

    List<ENTITY> list();

    List<ENTITY> search(IQuery businessObjectQuery);

    ENTITY deleteById(ID id);

    ENTITY get(ID identity);
}
