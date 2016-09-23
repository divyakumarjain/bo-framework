package org.divy.common.bo.command;

@FunctionalInterface
public interface ICreateCommand<E> extends ICommand
{
    E create(E entity);
}