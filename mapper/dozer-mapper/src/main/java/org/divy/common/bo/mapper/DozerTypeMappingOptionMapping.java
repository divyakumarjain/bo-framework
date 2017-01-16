package org.divy.common.bo.mapper;

import org.divy.common.bo.mapper.builder.options.OneWayMappingOption;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.dozer.loader.api.TypeMappingOption;
import org.dozer.loader.api.TypeMappingOptions;

import static org.dozer.loader.api.TypeMappingOptions.oneWay;


public enum DozerTypeMappingOptionMapping {

    OneWayMappingOptionMap(TypeMappingOptions.oneWay());

    private TypeMappingOption dozerOption;

    DozerTypeMappingOptionMapping(TypeMappingOption dozerOption) {
        this.dozerOption = dozerOption;
    }

    public static TypeMappingOption optionFor(MapperBuilderOption option) {
        if(option instanceof OneWayMappingOption) {
            return oneWay();
        } else {
            throw new UnsupportedOperationException("Does not support option: " + option.getClass());
        }
    }
}
