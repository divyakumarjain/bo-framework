package org.divy.common.bo.command;


public interface IGetCommand<ENTITY,ID> extends ICommand
{
    ENTITY get(ID identity);

}