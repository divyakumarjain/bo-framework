package org.divy.common.bo.business;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.query.IQuery;

import java.util.List;

public interface IBOManager<E extends IBusinessObject<I>, I> {

    E create(E businessObject);

    E update(E businessObject);

    E delete(E businessObject);

    List<E> list();

    List<E> search(IQuery businessObjectQuery);

    E deleteById(I id);

    E get(I identity);
}
