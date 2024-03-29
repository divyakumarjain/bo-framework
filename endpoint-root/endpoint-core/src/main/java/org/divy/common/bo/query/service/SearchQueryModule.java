package org.divy.common.bo.query.service;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.divy.common.bo.query.operator.Operator;

public class SearchQueryModule extends SimpleModule {

    private static final long serialVersionUID = -7766757771509630311L;

    public SearchQueryModule() {
        super();
        addDeserializer(Operator.class,new SearchQueryDeserializer() );
    }

}
