package org.divy.common.bo.database.command;


public interface DeleteCommand<E, I> extends Command
{
    E deleteById(I id);

    E delete(E entity);
}
