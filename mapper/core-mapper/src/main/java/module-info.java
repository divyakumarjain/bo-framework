module bo.framework.mapper {
    requires bo.framework.repository;
    requires bo.framework.metadata;


    exports org.divy.common.bo.mapper;
    exports org.divy.common.bo.mapper.builder;
    exports org.divy.common.bo.mapper.keyvaluemap;
}