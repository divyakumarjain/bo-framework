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
    requires com.fasterxml.jackson.databind;
    requires jersey.server;
    requires jakarta.ws.rs;
    requires org.slf4j;
    requires spring.beans;
    requires spring.boot;
    requires jakarta.validation;
    requires jersey.common;
    requires bo.framework.mapper;
    requires bo.framework.query;
}
