package org.divy.common.bo.spring.core.factory;

import org.divy.common.bo.repository.BusinessObject;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;

public interface BeanNamingStrategy {
    default String calculatePrefix(Class<? extends BusinessObject> type) {
        return Introspector.decapitalize(ClassUtils.getShortName(type));
    }

    String calculateManagerId(Class<? extends BusinessObject> type);

    String calculateCommandProviderId(Class<? extends BusinessObject> type);

    String calculateRepositoryId(Class<? extends BusinessObject> type);

    String calculateMergeMapperId(Class<? extends BusinessObject> type);

    String calculateKeyValueMapper(Class<? extends BusinessObject> type);

    String calculateHyperMediaMapperId(Class<? extends BusinessObject> type);

    String calculateAssociationsHandler(Class<? extends BusinessObject> type);
}
