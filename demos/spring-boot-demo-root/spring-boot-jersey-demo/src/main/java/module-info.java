module bo.framework.spring.boot.jersey.demo {
    requires bo.framework.database.jpa;
    requires java.persistence;
    requires java.validation;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context;
    requires spring.web;

    requires java.sql;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;

    opens org.divy.bo.demos.spring.jersey;

    exports org.divy.bo.demos.spring.jersey.greetings;
    exports org.divy.bo.demos.spring.jersey.cards;
}
