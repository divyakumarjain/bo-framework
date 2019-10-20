module bo.framework.endpoint.jersey {
    requires commons.lang;
    requires javax.servlet.api;
    requires bo.framework.endpoint.core;
    requires bo.framework.repository;
    requires bo.framework.metadata;
    requires bo.framework.mapper;
    requires bo.framework.exception.handling;
    requires bo.framework.query;
    requires com.fasterxml.jackson.databind;
    requires jakarta.inject;
    requires java.ws.rs;
    exports org.divy.common.rest;
    exports org.divy.common.rest.response;
    exports org.divy.common.rest.exception.mapper;
    exports org.divy.common.bo.endpoint.jersey.hatoas;
    exports org.divy.common.bo.endpoint.jersey.hatoas.association;
}
