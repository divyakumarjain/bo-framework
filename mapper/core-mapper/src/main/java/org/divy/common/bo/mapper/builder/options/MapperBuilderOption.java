package org.divy.common.bo.mapper.builder.options;

import org.divy.common.bo.mapper.builder.options.field.FieldMapperBuilderOption;
import org.divy.common.bo.mapper.builder.options.type.ClassMapperBuilderOption;

public sealed interface MapperBuilderOption permits OneWayMappingOption, FieldMapperBuilderOption, ClassMapperBuilderOption {
}

