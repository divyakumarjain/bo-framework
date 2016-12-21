package org.divy.common.bo.business;

import org.divy.common.bo.IBORepository;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.query.Query;

import java.util.List;

public class AbstractBOManager<E extends IBusinessObject<I>, I> implements IBOManager<E, I> {

    IBORepository<E, I> repository;

    public AbstractBOManager(IBORepository<E, I> repository) {
        this.repository = repository;
    }

    @Override
    public E create(E businessObject) {
        return repository.create(businessObject);
    }

    @Override
    public E update(I id, E businessObject) {
        return repository.update(id, businessObject);
    }

    @Override
    public E delete(E businessObject) {
        return repository.delete(businessObject);
    }

    @Override
    public List<E> list() {
        return repository.list();
    }

    @Override
    public List<E> search(Query businessObjectQuery) {
        return repository.search(businessObjectQuery);
    }

    @Override
    public E deleteById(I id) {
        return repository.deleteById(id);
    }

    @Override
    public E get(I identity) {
        return repository.get(identity);
    }

}
