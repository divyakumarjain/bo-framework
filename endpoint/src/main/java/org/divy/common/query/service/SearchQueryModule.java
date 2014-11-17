package org.divy.common.query.service;

import org.divy.common.bo.query.IOperator;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class SearchQueryModule extends SimpleModule {

    private static final long serialVersionUID = -7766757771509630311L;

    public SearchQueryModule() {
        super();
        addDeserializer(IOperator.class,new SearchQueryDeserializer() );
    }

}
