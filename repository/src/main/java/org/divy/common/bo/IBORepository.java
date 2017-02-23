package org.divy.common.bo;

import org.divy.common.bo.query.Query;

import java.util.List;
import java.util.stream.Stream;

public interface IBORepository<E extends IBusinessObject<I>, I>
{
    E create(E businessObject);

    E update(I id, E businessObject);

    E delete(E businessObject);

    List<E> list();

    List<E> search(Query businessObjectQuery);

    Stream<E> stream();

    Stream<E> searchStream(Query businessObjectQuery);

    E deleteById(I id);

    E get(I identity);

}
