module bo.framework.endpoint.core {
    requires javax.inject;
    requires bo.framework.repository;
    requires bo.framework.exception.handling;
    requires bo.framework.businessapi;
    requires bo.framework.query;
    requires bo.framework.dynamic;
    requires bo.framework.metadata;
    requires javax.servlet.api;
    requires slf4j.api;
    requires jackson.annotations;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires commons.lang;
    requires bo.framework.mapper;
    exports org.divy.common.bo.endpoint;
    exports org.divy.common.bo.endpoint.hypermedia;
    exports org.divy.common.bo.rest;
    exports org.divy.common.bo.rest.response;
    exports org.divy.common.bo.http;
    exports org.divy.common.bo.endpoint.hypermedia.association;
    exports org.divy.common.bo.endpoint.hypermedia.association.builder;
}
