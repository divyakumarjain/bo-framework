module bo.framework.endpoint.jersey {
    requires bo.framework.endpoint.core;
    requires bo.framework.repository;
    requires bo.framework.metadata;
    requires bo.framework.mapper;
    requires bo.framework.exception.handling;
    requires bo.framework.query;
    requires jakarta.inject;
    requires java.ws.rs;
    exports org.divy.common.bo.graphql;
}
