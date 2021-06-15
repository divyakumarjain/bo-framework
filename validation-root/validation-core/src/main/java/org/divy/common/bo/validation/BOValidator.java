package org.divy.common.bo.validation;

import org.divy.common.bo.repository.BusinessObject;

public interface BOValidator<B extends BusinessObject<I>, I> {
    ValidationResults<B,I> validate(B businessObject);
    ValidationResults<B,I> validate(B businessObject, Class<?> validationGroup);
    ValidationResults<B,I> validateCreate(B businessObject);
    ValidationResults<B,I> validateUpdate(B businessObject);
    ValidationResults<B,I> validateDelete(B businessObject);
}
