module bo.framework.validation {
    requires bo.framework.repository;
    requires bo.framework.exception.handling;
    requires java.validation;

    exports org.divy.common.bo.validation;
    exports org.divy.common.bo.validation.group;
}
