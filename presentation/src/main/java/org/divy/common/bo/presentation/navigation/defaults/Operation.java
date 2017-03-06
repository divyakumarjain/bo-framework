package org.divy.common.bo.presentation.navigation.defaults;

import org.divy.common.bo.presentation.navigation.IOperation;

public class Operation implements IOperation {

    String entity;
    String label;
    String operation;

    public Operation(String entity, String operation) {
        super();
        this.entity = entity;
        this.operation = operation;
    }

    public Operation(String entityName, String listOperation, String operationLabel) {
        this(entityName,listOperation);
        this.label = operationLabel;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getEntity() {
        return entity;
    }

    @Override
    public void setEntity(String entity) {
        this.entity = entity;
    }

    @Override
    public String getOperation() {
        return operation;
    }

    @Override
    public void setOperation(String operation) {
        this.operation = operation;
    }
}
