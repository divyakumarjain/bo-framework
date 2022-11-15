module bo.framework.spring.mvc {
    requires bo.framework.query;
    requires bo.framework.mapper;
    requires bo.framework.dynamic;
    requires bo.framework.metadata;
    requires bo.framework.repository;
    requires bo.framework.businessapi;
    requires bo.framework.endpoint.core;
    requires bo.framework.exception.handling;
    requires bo.framework.spring.support.core;
    requires org.slf4j;
    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.hateoas;
    requires spring.boot.autoconfigure;
    requires commons.lang;
    requires com.fasterxml.jackson.datatype.jdk8;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.module.paramnames;
    requires com.fasterxml.jackson.databind;
    requires javax.servlet.api;

    exports org.divy.common.bo.spring.mvc.rest.config;
    exports org.divy.common.bo.spring.mvc.rest;
}
