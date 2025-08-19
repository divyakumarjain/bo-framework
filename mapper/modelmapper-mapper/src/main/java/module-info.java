module bo.framework.modelmapper.mapper {
    requires modelmapper;
    requires bo.framework.repository;
    requires bo.framework.metadata;
    requires bo.framework.mapper;

    exports org.divy.common.bo.mapper.modelmapper.builder;
    exports org.divy.common.bo.mapper.modelmapper;
    exports org.divy.common.bo.mapper.defaults;
}
