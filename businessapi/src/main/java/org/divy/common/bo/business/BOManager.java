package org.divy.common.bo.business;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.query.Query;

import java.util.List;
import java.util.Optional;

public interface BOManager<E extends BusinessObject<I>, I> {

    E create(E businessObject);

    E update(I id, E businessObject);

    E delete(E businessObject);
    List<E> list();

    List<E> search(Query businessObjectQuery);

    E deleteById(I id);

    Optional<E> get(I identity);
}
