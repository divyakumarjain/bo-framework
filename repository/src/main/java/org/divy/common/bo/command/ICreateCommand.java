package org.divy.common.bo.command;


public interface ICreateCommand<E, I> extends ICommand
{
    E create(E entity);
}