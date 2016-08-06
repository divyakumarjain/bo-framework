package org.divy.common.bo.command;


public interface IGetCommand<E, I> extends ICommand
{
    E get(I identity);

}