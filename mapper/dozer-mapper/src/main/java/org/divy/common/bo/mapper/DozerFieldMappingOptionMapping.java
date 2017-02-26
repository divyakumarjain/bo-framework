package org.divy.common.bo.mapper;

import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.divy.common.bo.mapper.builder.options.OneWayMappingOption;
import org.divy.common.bo.mapper.builder.options.field.FieldHintA;
import org.divy.common.bo.mapper.builder.options.field.FieldHintB;
import org.dozer.loader.api.FieldsMappingOption;

import static org.dozer.loader.api.FieldsMappingOptions.*;



public class DozerFieldMappingOptionMapping {

    public static FieldsMappingOption optionFor(MapperBuilderOption option) {
        if (option instanceof FieldHintA) {
            FieldHintA hintA = (FieldHintA) option;
            return hintA(hintA.getHintClass());
        } if (option instanceof FieldHintB) {
            FieldHintB hintB = (FieldHintB) option;
            return hintB(hintB.getHintClass());
        } if(option instanceof OneWayMappingOption) {
            return oneWay();
        } else {
            throw new UnsupportedOperationException("Does not support option: " + option.getClass());
        }
    }
}
