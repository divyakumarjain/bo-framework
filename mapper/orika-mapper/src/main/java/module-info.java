module bo.framework.orika.mapper {
    requires orika.core;
    requires bo.framework.repository;
    requires bo.framework.metadata;
    requires bo.framework.mapper;

    exports org.divy.common.bo.mapper.orika.builder;
    exports org.divy.common.bo.mapper.orika;
}
