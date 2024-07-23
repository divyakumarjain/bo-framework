module bo.framework.spring.boot.mvc.demo {
    requires bo.framework.database.jpa;
    requires jakarta.persistence;
    requires jakarta.validation;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context;
    requires bo.framework.orika.mapper;
    requires bo.framework.demo.domain;

    requires com.fasterxml.jackson.databind;

    opens org.divy.bo.demos.spring.mvc;
}
