module bo.framework.endpoint.mock {
    requires bo.framework.repository;
    requires bo.framework.businessapi;
    requires bo.framework.query;
    requires bo.framework.database.core;
    requires bo.framework.mock.common;
    requires bo.framework.endpoint.core;

    requires jakarta.servlet;
    requires jakarta.ws.rs;
    requires jakarta.xml.bind;
    requires jersey.common;
    requires jersey.server;

    requires jersey.test.framework.core;
    requires jersey.test.framework.provider.jetty;

    requires org.junit.jupiter.api;
    requires org.hamcrest;
    requires org.mockito;
    requires com.google.guice;
    requires com.google.guice.extensions.servlet;
    requires bo.framework.exception.handling;
    requires bo.framework.endpoint.jersey;
    exports org.divy.common.bo.endpoint.test.json to junit;
    exports org.divy.common.bo.endpoint.test to junit;
}
