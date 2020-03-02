module bo.framework.endpoint.core {
    requires jakarta.validation;
    requires bo.framework.repository;
    requires bo.framework.exception.handling;
    requires bo.framework.businessapi;
    requires bo.framework.query;
    requires bo.framework.dynamic;
    requires bo.framework.metadata;
    requires org.slf4j;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires bo.framework.mapper;
    requires jakarta.inject;
    requires jakarta.servlet;
    exports org.divy.common.bo.endpoint;
    exports org.divy.common.bo.endpoint.hatoas;
    exports org.divy.common.bo.rest;
    exports org.divy.common.bo.rest.response;
    exports org.divy.common.bo.http;
    exports org.divy.common.bo.endpoint.hatoas.association;
    exports org.divy.common.bo.endpoint.hatoas.association.builder;
}
