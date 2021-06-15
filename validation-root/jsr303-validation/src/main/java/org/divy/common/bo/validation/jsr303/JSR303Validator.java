package org.divy.common.bo.validation.jsr303;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.validation.AbstractBOValidator;
import org.divy.common.bo.validation.ValidationResults;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collection;

public class JSR303Validator extends AbstractBOValidator {

    Validator validator;

    public JSR303Validator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public ValidationResults validate(BusinessObject businessObject) {
        final Collection<ConstraintViolation<BusinessObject>> results = validator.validate(businessObject);
        final var validationResults = new ValidationResults();
        validationResults.addConstraintViolation(results);
        return validationResults;
    }

    @Override
    public ValidationResults validate(BusinessObject businessObject, Class validationGroup) {
        final Collection<ConstraintViolation<BusinessObject>> results = validator.validate(businessObject, validationGroup);
        final var validationResults = new ValidationResults();
        validationResults.addConstraintViolation(results);
        return validationResults;
    }
}
