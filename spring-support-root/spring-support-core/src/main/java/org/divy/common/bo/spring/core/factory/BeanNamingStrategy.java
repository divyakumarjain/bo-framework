package org.divy.common.bo.spring.core.factory;

import org.divy.common.bo.IBusinessObject;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;

public interface BeanNamingStrategy {
    default String calculatePrefix(Class<? extends IBusinessObject> type) {
        return Introspector.decapitalize(ClassUtils.getShortName(type));
    }

    String calculateManagerId(Class<? extends IBusinessObject> type);

    String calculateCommandProviderId(Class<? extends IBusinessObject> type);

    String calculateRepositoryId(Class<? extends IBusinessObject> type);

    String calculateMergeMapperId(Class<? extends IBusinessObject> type);

    String calculateKeyValueMapper(Class<? extends IBusinessObject> type);

    String calculateHyperMediaMapperId(Class<? extends IBusinessObject> type);
}
