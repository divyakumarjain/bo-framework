package org.divy.common.bo.endpoint.hatoas.association;

import org.divy.common.bo.repository.BusinessObject;

import java.util.List;
import java.util.Optional;

public interface AssociationsHandler<T extends BusinessObject<I>, I>
{
    Optional<Association<T, I>> getAssociation(String relation);

    List<Association<T, I>> getAssociations();
}