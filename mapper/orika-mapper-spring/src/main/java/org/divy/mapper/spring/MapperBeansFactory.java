package org.divy.mapper.spring;

import org.divy.common.bo.IBusinessObject;

public interface MapperBeansFactory {
    String calculateMergeMapperId(Class<? extends IBusinessObject> type);
}
