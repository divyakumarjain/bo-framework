package org.divy.common.bo.spring;

import org.divy.common.bo.IBusinessObject;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;

public interface DynamicBeanFactory<T> {
    void register(T dynamicBeanFactory, BeanDefinitionRegistry beanDefinitionRegistry);
    default String calculatePrefix(Class<? extends IBusinessObject> type) {
        return Introspector.decapitalize(ClassUtils.getShortName(type));
    }
}
