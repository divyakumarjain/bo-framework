module bo.framework.database.jpa {
    requires jakarta.persistence;
    requires bo.framework.database.core;
    requires bo.framework.repository;
    requires bo.framework.mapper;
    requires bo.framework.query;
    requires bo.framework.metadata;
    requires org.slf4j;
    requires java.desktop;
    exports org.divy.common.bo.database.jpa;
    exports org.divy.common.bo.database.jpa.defaults;
    exports org.divy.common.bo.database.jpa.context;
}
