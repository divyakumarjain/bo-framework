package org.divy.common.bo.command;

@FunctionalInterface
public interface IGetCommand<E, I> extends ICommand
{
    E get(I identity);

}