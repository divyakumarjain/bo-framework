package org.divy.common.bo.business;

import org.divy.common.bo.repository.BORepository;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.validation.BOValidationException;
import org.divy.common.bo.validation.BOValidator;
import org.divy.common.bo.validation.ValidationResults;
import org.divy.common.bo.validation.group.BOCreateCheck;
import org.divy.common.bo.validation.group.BOUpdateCheck;
import org.divy.common.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public class AbstractBOManager<E extends BusinessObject<I>, I> implements BOManager<E,I> {

    public static final String BO_VALIDATION_CODE = "BO-VALIDATION";

    final BORepository<E, I> repository;
    final BOValidator<E, I> validator;

    public AbstractBOManager(BORepository<E, I> repository, BOValidator<E, I> boValidator) {
        this.repository = repository;
        this.validator = boValidator;
    }

    public E create( E businessObject) {
        ValidationResults results = doValidate(businessObject, BOCreateCheck.class);
        if (results.isEmpty())
            return repository.create(businessObject);
        else {
            throw new BOValidationException( buildFormattedMessage( businessObject ), BO_VALIDATION_CODE, results);
        }
    }

    private String buildFormattedMessage( E businessObject )
    {
        return String.format( "Validation for Business object %s failed", businessObject.identity() );
    }

    private ValidationResults doValidate(E businessObject, Class checkGroupClass) {
        if (validator!=null) {
            return this.validator.validate(businessObject,checkGroupClass);
        } else {
            return new ValidationResults();
        }
    }

    public E update(I id, E businessObject) {
        ValidationResults results = doValidate(businessObject, BOUpdateCheck.class);
        if (results.isEmpty()) {
            return repository.update(id, businessObject);
        }
        else {
            throw new BOValidationException( buildFormattedMessage( businessObject ), BO_VALIDATION_CODE, results);
        }
    }

    public E delete( E businessObject) {
        ValidationResults results = doValidate(businessObject, BOUpdateCheck.class);
        if (results.isEmpty()) {
            return repository.delete(businessObject);
        }
        else {
            throw new BOValidationException( buildFormattedMessage( businessObject ), BO_VALIDATION_CODE, results);
        }
    }

    public List<E> list() {
        return repository.list();
    }

    public List<E> search(Query businessObjectQuery) {
        return repository.search(businessObjectQuery);
    }

    public E deleteById(I id) {
        var businessObject = get(id).orElseThrow( () -> new NotFoundException("Could not find the entity") );
        ValidationResults results = doValidate(businessObject, BOUpdateCheck.class);
        if (results.isEmpty()) {
            return repository.deleteById(id);
        }
        else {
            throw new BOValidationException( buildFormattedMessage( businessObject ), BO_VALIDATION_CODE, results);
        }
    }

    public Optional<E> get(I identity) {
        return repository.get(identity);
    }

}
