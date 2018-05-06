package org.divy.common.bo.database.command;

@FunctionalInterface
public interface UpdateCommand<E, I> extends Command
{
    E update(I id, E user);
}