package org.divy.common.bo.business;

import org.divy.common.bo.BORepository;
import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.business.validation.BOValidationException;
import org.divy.common.bo.business.validation.BOValidator;
import org.divy.common.bo.business.validation.ValidationResults;
import org.divy.common.bo.query.Query;

import java.util.List;

public class AbstractBOManager<E extends BusinessObject<I>, I> implements BOManager<E, I> {

    final BORepository<E, I> repository;
    final BOValidator<E, I> validator;

    public AbstractBOManager(BORepository<E, I> repository, BOValidator<E, I> boValidator) {
        this.repository = repository;
        this.validator = boValidator;
    }

    @Override
    public E create(E businessObject) {
        ValidationResults results = doValidate(businessObject);
        if(results.isEmpty())
            return repository.create(businessObject);
        else {
            throw new BOValidationException("Could not validate Business object " + businessObject.identity(), "BO-VALIDATION", results);
        }
    }

    private ValidationResults doValidate(E businessObject) {
        if(validator!=null) {
            return this.validator.validate(businessObject);
        } else {
            return new ValidationResults();
        }
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
