package org.divy.common.bo.spring.core.factory;

import org.divy.common.bo.IBusinessObject;

public class BeanNamingStrategyImpl implements BeanNamingStrategy {

    @Override
    public String calculateManagerId(Class<? extends IBusinessObject> type) {
        return calculatePrefix(type) + "Manager";
    }

    @Override
    public String calculateCommandProviderId(Class<? extends IBusinessObject> type) {
        return calculatePrefix(type) + "CommandProvider";
    }
    @Override
    public String calculateRepositoryId(Class<? extends IBusinessObject> type) {
        return calculatePrefix(type) + "Repository";
    }

    @Override
    public String calculateMergeMapperId(Class<? extends IBusinessObject> type) {
        return calculatePrefix(type) + "MergeMapper";
    }

    @Override
    public String calculateKeyValueMapper(Class<? extends IBusinessObject> type) {
        return calculatePrefix(type) + "Mapper";
    }

    @Override
    public String calculateHyperMediaMapperId(Class<? extends IBusinessObject> type) {
        return calculatePrefix(type) + "HyperMediaMapper";
    }
}
