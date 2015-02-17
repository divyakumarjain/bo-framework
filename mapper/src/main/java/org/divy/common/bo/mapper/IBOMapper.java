package org.divy.common.bo.mapper;

import java.util.Collection;

public interface IBOMapper<BO,OTHER> {

    BO createBO(OTHER sourceData);
    BO mapToBO(OTHER sourceData,BO businessObject);

    OTHER createFromBO(BO businessObject);
    OTHER mapFromBO(BO businessObject, OTHER targetData);

    Collection<BO> createBO(Collection<OTHER> sourceData);

    Collection<OTHER> createFromBO(Collection<BO> businessObject);
}
