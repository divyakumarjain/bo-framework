module bo.framework.endpoint.core {
    requires bo.framework.repository;
    requires bo.framework.exception.handling;
    requires bo.framework.businessapi;
    requires bo.framework.query;
    requires bo.framework.dynamic;
    requires bo.framework.metadata;
    requires javax.servlet.api;
    requires org.slf4j;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires commons.lang;
    requires bo.framework.mapper;
    requires jakarta.inject;
    exports org.divy.common.bo.endpoint;
    exports org.divy.common.bo.endpoint.hypermedia;
    exports org.divy.common.bo.rest;
    exports org.divy.common.bo.rest.response;
    exports org.divy.common.bo.http;
    exports org.divy.common.bo.endpoint.hypermedia.association;
    exports org.divy.common.bo.endpoint.hypermedia.association.builder;
}
