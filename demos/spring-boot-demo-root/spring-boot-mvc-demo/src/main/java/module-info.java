module bo.framework.spring.boot.mvc.demo {
    requires bo.framework.database.jpa;
    requires java.persistence;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context;

    requires com.fasterxml.jackson.databind;

    opens org.divy.bo.demos.spring.mvc;
}
