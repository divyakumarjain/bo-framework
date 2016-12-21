package org.divy.common.bo.command;


public interface IUpdateCommand<E, I> extends ICommand
{
    E update(I id, E user);

}