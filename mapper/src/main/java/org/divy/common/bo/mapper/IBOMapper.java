package org.divy.common.bo.mapper;

import java.util.List;

public interface IBOMapper<BO,OTHER> {

    BO createBO(OTHER sourceData);
    BO mapToBO(OTHER sourceData,BO businessObject);

    OTHER createFromBO(BO businessObject);
    OTHER mapFromBO(BO businessObject, OTHER targetData);

    List<BO> createBO(List<OTHER> sourceData);

    List<OTHER> createFromBO(List<BO> businessObject);
}
