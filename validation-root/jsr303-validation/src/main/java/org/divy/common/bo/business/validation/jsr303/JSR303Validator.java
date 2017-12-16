package org.divy.common.bo.business.validation.jsr303;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.business.validation.BOValidator;
import org.divy.common.bo.business.validation.ValidationResults;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collection;

public class JSR303Validator implements BOValidator {

    Validator validator;

    public JSR303Validator(Validator validator) {
        this.validator = validator;
    }
    @Override
    public ValidationResults validate(BusinessObject businessObject) {
        final Collection<ConstraintViolation<BusinessObject>> results = validator.validate(businessObject);
        final ValidationResults validationResults = new ValidationResults();
        validationResults.addConstraintViolation(results);
        return validationResults;
    }
}
