package org.divy.common.bo.validation;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.validation.group.BOCreateCheck;
import org.divy.common.bo.validation.group.BODeleteCheck;
import org.divy.common.bo.validation.group.BOUpdateCheck;

public abstract class AbstractBOValidator<B extends BusinessObject<I>, I> implements BOValidator<B, I> {
    @Override
    public ValidationResults validateCreate(B businessObject)
    {
        return validate(businessObject, BOCreateCheck.class);
    }

    @Override
    public ValidationResults validateUpdate(B businessObject)
    {
        return validate(businessObject, BOUpdateCheck.class);
    }

    @Override
    public ValidationResults validateDelete(B businessObject)
    {
        return validate(businessObject, BODeleteCheck.class);
    }
}
