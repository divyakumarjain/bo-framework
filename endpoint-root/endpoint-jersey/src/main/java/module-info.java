module bo.framework.endpoint.jersey {
    requires bo.framework.endpoint.core;
    requires bo.framework.repository;
    requires bo.framework.metadata;
    requires bo.framework.mapper;
    requires bo.framework.exception.handling;
    requires bo.framework.query;
    requires bo.framework.dynamic;
    requires com.fasterxml.jackson.databind;
    requires jakarta.servlet;
    requires jakarta.ws.rs;
    requires jakarta.inject;
    exports org.divy.common.bo.jersey.rest;
    exports org.divy.common.bo.jersey.rest.response;
    exports org.divy.common.bo.jersey.rest.exception.mapper;
    exports org.divy.common.bo.jersey.rest.hatoas;
    exports org.divy.common.bo.jersey.rest.hatoas.association;
}
