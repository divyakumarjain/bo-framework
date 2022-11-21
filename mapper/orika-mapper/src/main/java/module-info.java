module bo.framework.orika.mapper {

    requires bo.framework.repository;
    requires bo.framework.metadata;
    requires bo.framework.mapper;
    requires orika.core;

    exports org.divy.common.bo.mapper.orika.builder;
    exports org.divy.common.bo.mapper.orika;
}
