module bo.framework.spring.jersey.endpoint.support {
    requires bo.framework.spring.support.core;
    requires bo.framework.endpoint.jersey;
    requires bo.framework.endpoint.core;
    requires bo.framework.businessapi;
    requires bo.framework.repository;
    requires bo.framework.metadata;
    requires bo.framework.dynamic;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.web;
    requires com.fasterxml.jackson.module.paramnames;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.datatype.jdk8;
    requires jersey.server;
    requires java.ws.rs;
    requires slf4j.api;
    requires spring.beans;
    requires spring.boot;
    requires java.validation;
    requires jersey.common;
}
