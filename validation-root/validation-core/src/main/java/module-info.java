module bo.framework.validation {
    requires bo.framework.repository;
    requires bo.framework.exception.handling;
    requires jakarta.validation;

    exports org.divy.common.bo.validation;
    exports org.divy.common.bo.validation.group;
}
