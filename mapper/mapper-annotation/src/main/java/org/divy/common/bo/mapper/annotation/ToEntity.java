package org.divy.common.bo.mapper.annotation;

import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
@Mapping(target = "id", ignore = true)
@Mapping(target = "lastUpdateTimestamp", ignore = true)
@Mapping(target = "createTimestamp", ignore = true)
@Mapping(target = "uuid", ignore = true)
public @interface ToEntity { }
