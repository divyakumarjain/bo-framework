
module bo.framework.validation.jsr303.spring {
    exports org.divy.common.bo.validation.spring.autoconfiguration;
    requires bo.framework.validation;
    requires bo.framework.validation.jsr303;
    requires bo.framework.repository;
    requires spring.context;
    requires jakarta.validation;
}
