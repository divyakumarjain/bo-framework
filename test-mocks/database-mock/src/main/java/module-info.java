module bo.framework.database.mock {
    requires bo.framework.repository;
    requires bo.framework.query;
    requires bo.framework.mock.common;
    requires org.junit.jupiter.api;
    requires org.hamcrest;
    exports org.divy.common.bo.repository.test;
}