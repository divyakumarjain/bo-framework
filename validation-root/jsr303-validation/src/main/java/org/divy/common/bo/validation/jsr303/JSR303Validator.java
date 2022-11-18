package org.divy.common.bo.validation.jsr303;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.validation.AbstractBOValidator;
import org.divy.common.bo.validation.ValidationResults;

import jakarta.validation.Validator;

public class JSR303Validator<B extends BusinessObject<I>, I> extends AbstractBOValidator<B, I> {

    Validator validator;

    public JSR303Validator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public ValidationResults<B, I> validate(B businessObject) {
        final var validationResults = new ValidationResults<B,I>();
        validationResults.addConstraintViolation(validator.validate(businessObject));
        return validationResults;
    }

    @Override
    public ValidationResults<B, I> validate(B businessObject, Class<?> validationGroup) {
        final var validationResults = new ValidationResults<B,I>();
        validationResults.addConstraintViolation(validator.validate(businessObject, validationGroup));
        return validationResults;
    }
}
