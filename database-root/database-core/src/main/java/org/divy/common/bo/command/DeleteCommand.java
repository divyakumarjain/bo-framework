package org.divy.common.bo.command;


public interface DeleteCommand<E, I> extends Command
{
    E deleteById(I id);

    E delete(E entity);
}
