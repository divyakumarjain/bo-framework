package org.divy.common.bo.command;


public interface IDeleteCommand <ENTITY,ID> extends ICommand
{
    ENTITY deleteById(ID id);
    ENTITY delete(ENTITY entity);
}
