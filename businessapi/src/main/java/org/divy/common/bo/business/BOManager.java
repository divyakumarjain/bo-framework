package org.divy.common.bo.business;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.validation.BOValidationException;

import java.util.List;

public interface BOManager<E extends BusinessObject<I>, I> {

    E create(E businessObject)throws BOValidationException;

    E update(I id, E businessObject);

    E delete(E businessObject);
    List<E> list();

    List<E> search(Query businessObjectQuery);

    E deleteById(I id);

    E get(I identity);
}
