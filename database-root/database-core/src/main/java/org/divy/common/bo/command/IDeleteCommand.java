package org.divy.common.bo.command;


public interface IDeleteCommand<E, I> extends ICommand
{
    E deleteById(I id);

    E delete(E entity);
}
