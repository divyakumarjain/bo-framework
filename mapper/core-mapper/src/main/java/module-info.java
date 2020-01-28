module bo.framework.mapper {
    requires bo.framework.repository;
    requires bo.framework.metadata;


    exports org.divy.common.bo.mapper;
    exports org.divy.common.bo.mapper.builder;
    exports org.divy.common.bo.mapper.keyvaluemap;
    exports org.divy.common.bo.mapper.builder.options.field;
    exports org.divy.common.bo.mapper.builder.options;
    exports org.divy.common.bo.mapper.builder.options.type;
}
