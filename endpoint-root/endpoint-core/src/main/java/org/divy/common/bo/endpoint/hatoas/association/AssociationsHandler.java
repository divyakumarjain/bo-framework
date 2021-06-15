package org.divy.common.bo.endpoint.hatoas.association;

import org.divy.common.bo.repository.BusinessObject;

import java.util.List;
import java.util.Optional;

public interface AssociationsHandler<T extends BusinessObject<I>, I, L>
{
    Optional<Association<T, I, L>> getAssociation(String relation);

    List<Association<T, I, L>> getAssociations();
}
