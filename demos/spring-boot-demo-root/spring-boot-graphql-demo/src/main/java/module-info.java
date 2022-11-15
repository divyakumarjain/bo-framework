module bo.framework.spring.boot.mvc.graphql.demo {
    requires bo.framework.database.jpa;
    requires java.persistence;
    requires java.validation;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context;
    requires bo.framework.orika.mapper;
    requires bo.framework.demo.domain;

    requires com.fasterxml.jackson.databind;

    opens org.divy.bo.demos.spring.mvc.graphql;
}
