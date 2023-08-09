package org.divy.common.bo.mapper.builder.options.type;

public final class TypeFactoryOption implements ClassMapperBuilderOption {
    final Factory factory;
    public TypeFactoryOption(Factory factory) {
        this.factory = factory;
    }

    public Factory getFactory() {
        return this.factory;
    }
}

