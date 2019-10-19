module bo.framework.mock.common {
    requires bo.framework.query;
    requires bo.framework.repository;
    requires junit;
    requires slf4j.api;
    requires org.hamcrest;
    exports org.divy.common.bo.test;
}
