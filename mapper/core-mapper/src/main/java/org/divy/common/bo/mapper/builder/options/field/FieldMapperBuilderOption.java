package org.divy.common.bo.mapper.builder.options.field;

import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.divy.common.bo.mapper.builder.options.type.ChildTypeMapperOption;

public sealed interface FieldMapperBuilderOption extends MapperBuilderOption permits FieldConverter, FieldConverterByName, FieldExclude, FieldFactoryOption, FieldHint, TargetFieldName, ChildTypeMapperOption {
}
