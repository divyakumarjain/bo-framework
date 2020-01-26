package org.divy.common.bo.spring.core.factory;

import org.divy.common.bo.repository.BusinessObject;

public class BeanNamingStrategyImpl implements BeanNamingStrategy {

    @Override
    public String calculateManagerId(Class<? extends BusinessObject> type) {
        return calculatePrefix(type) + "Manager";
    }

    @Override
    public String calculateCommandProviderId(Class<? extends BusinessObject> type) {
        return calculatePrefix(type) + "CommandProvider";
    }
    @Override
    public String calculateRepositoryId(Class<? extends BusinessObject> type) {
        return calculatePrefix(type) + "Repository";
    }

    @Override
    public String calculateMergeMapperId(Class<? extends BusinessObject> type) {
        return calculatePrefix(type) + "MergeMapper";
    }

    @Override
    public String calculateKeyValueMapper(Class<? extends BusinessObject> type) {
        return calculatePrefix(type) + "Mapper";
    }

    @Override
    public String calculateHATOASMapperId(Class<? extends BusinessObject> type) {
        return calculatePrefix(type) + "HATOASMapper";
    }

    @Override
    public String calculateAssociationsHandler(Class<? extends BusinessObject> type) {
        return calculatePrefix(type) + "AssociationsHandler";
    }
}
