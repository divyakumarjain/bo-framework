package org.divy.common.bo.presentation.navigation.defaults;

import java.util.ArrayList;

public class BOOperationApplicationSection extends ApplicationSection {

    private static final String ADD_OPERATION = "add";
    private static final String UPDATE_OPERATION = "update";
    private static final String LIST_OPERATION = "list";

    private final String entityName;

    public BOOperationApplicationSection(String entityName) {
        super(entityName);
        this.entityName = entityName.toLowerCase();
        init();
    }

    protected void init() {
        operations = new ArrayList<>();
        operations.add(new Operation(entityName, LIST_OPERATION,sectionLabel+"(s)"));
        operations.add(new Operation(entityName, ADD_OPERATION,"Create "+sectionLabel));
        operations.add(new Operation(entityName, UPDATE_OPERATION));
    }
}
