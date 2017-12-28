module bo.framework.database.core {
    requires bo.framework.repository;
    requires bo.framework.exception.handling;
    requires bo.framework.query;
    requires bo.framework.mapper;

    exports org.divy.common.bo.database;
    exports org.divy.common.bo.database.command;
    exports org.divy.common.bo.database.command.impl to bo.framework.database.jpa;
    exports org.divy.common.bo.database.context;
}