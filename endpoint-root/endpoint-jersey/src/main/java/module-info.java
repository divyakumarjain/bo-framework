module bo.framework.endpoint.jersey {
    requires commons.lang;
    requires jakarta.servlet;
    requires jakarta.inject;
    requires bo.framework.endpoint.core;
    requires bo.framework.repository;
    requires bo.framework.metadata;
    requires bo.framework.mapper;
    requires bo.framework.exception.handling;
    requires bo.framework.query;
    requires com.fasterxml.jackson.databind;
    requires jakarta.ws.rs;
    exports org.divy.common.bo.jersey.rest;
    exports org.divy.common.bo.jersey.rest.response;
    exports org.divy.common.bo.jersey.rest.exception.mapper;
    exports org.divy.common.bo.jersey.rest.hatoas;
    exports org.divy.common.bo.jersey.rest.hatoas.association;
}
