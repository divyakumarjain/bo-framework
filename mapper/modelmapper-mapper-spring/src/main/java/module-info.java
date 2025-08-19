module bo.framework.modelmapper.spring {
    requires bo.framework.mapper;
    requires bo.framework.modelmapper.mapper;
    requires bo.framework.spring.support.core;
    requires bo.framework.repository;
    requires bo.framework.metadata;
    requires spring.context;
    requires spring.beans;
    requires spring.boot.autoconfigure;
    
    exports org.divy.common.bo.spring.mapper.factory;
}
