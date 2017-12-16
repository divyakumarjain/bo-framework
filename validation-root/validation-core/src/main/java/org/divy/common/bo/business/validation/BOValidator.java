package org.divy.common.bo.business.validation;

import org.divy.common.bo.BusinessObject;

@FunctionalInterface
public interface BOValidator<B extends BusinessObject<I>, I> {
    ValidationResults validate(B businessObject);
}
