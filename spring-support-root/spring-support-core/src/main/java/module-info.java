module bo.framework.spring.support.core {
    exports org.divy.common.bo.spring.core.factory;
    requires bo.framework.repository;
    requires spring.core;
    requires java.desktop;
    requires bo.framework.metadata;
    requires spring.beans;
    requires slf4j.api;
    requires spring.context;
}
