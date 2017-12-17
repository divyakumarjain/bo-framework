package org.divy.common.bo.business;

import org.divy.common.bo.BORepository;
import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.validation.BOValidationException;
import org.divy.common.bo.validation.BOValidator;
import org.divy.common.bo.validation.ValidationResults;
import org.divy.common.bo.validation.group.BOCreateCheck;
import org.divy.common.bo.validation.group.BOUpdateCheck;

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
        ValidationResults results = doValidate(businessObject, BOCreateCheck.class);
        if (results.isEmpty())
            return repository.create(businessObject);
        else {
            throw new BOValidationException("Validation for Business object " + businessObject.identity() + " failed", "BO-VALIDATION", results);
        }
    }

    private ValidationResults doValidate(E businessObject, Class checkGroupClass) {
        if (validator!=null) {
            return this.validator.validate(businessObject,checkGroupClass);
        } else {
            return new ValidationResults();
        }
    }

    @Override
    public E update(I id, E businessObject) {
        ValidationResults results = doValidate(businessObject, BOUpdateCheck.class);
        if (results.isEmpty()) {
            return repository.update(id, businessObject);
        }
        else {
            throw new BOValidationException("Validation for Business object " + businessObject.identity() + " failed", "BO-VALIDATION", results);
        }
    }

    @Override
    public E delete(E businessObject) {
        ValidationResults results = doValidate(businessObject, BOUpdateCheck.class);
        if (results.isEmpty()) {
            return repository.delete(businessObject);
        }
        else {
            throw new BOValidationException("Validation for Business object " + businessObject.identity() + " failed", "BO-VALIDATION", results);
        }
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
        final E businessObject = get(id);
        ValidationResults results = doValidate(businessObject, BOUpdateCheck.class);
        if (results.isEmpty()) {
            return repository.deleteById(id);
        }
        else {
            throw new BOValidationException("Validation for Business object " + businessObject.identity() + " failed", "BO-VALIDATION", results);
        }
    }

    @Override
    public E get(I identity) {
        return repository.get(identity);
    }

}
