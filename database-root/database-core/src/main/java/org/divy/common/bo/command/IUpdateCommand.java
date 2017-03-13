package org.divy.common.bo.command;

@FunctionalInterface
public interface IUpdateCommand<E, I> extends ICommand
{
    E update(I id, E user);
}