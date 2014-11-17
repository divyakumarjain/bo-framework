package org.divy.common.bo.presentation.navigation;

public interface IOperation {

    public abstract void setOperation(String operation);

    public abstract String getOperation();

    public abstract void setEntity(String entity);

    public abstract String getEntity();

    public abstract String getLabel();

}
