package org.divy.common.bo.command;

@FunctionalInterface
public interface UpdateCommand<E, I> extends Command
{
    E update(I id, E user);
}