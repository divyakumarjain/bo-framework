module bo.framework.endpoint.mock {
    requires bo.framework.repository;
    requires bo.framework.businessapi;
    requires bo.framework.query;
    requires bo.framework.database.core;
    requires bo.framework.mock.common;
    requires bo.framework.endpoint.jersey;
    requires bo.framework.endpoint.core;

    requires javax.servlet.api;
    requires java.ws.rs;
    requires jersey.common;
    requires jersey.server;
    requires java.activation;

    requires jersey.test.framework.core;
    requires jersey.test.framework.provider.jetty;

    requires junit;
    requires org.hamcrest;
    requires org.mockito;
    requires com.google.guice;
    requires com.google.guice.extensions.servlet;
    exports org.divy.common.bo.endpoint.test.json to junit;
    exports org.divy.common.bo.endpoint.test to junit;
}
