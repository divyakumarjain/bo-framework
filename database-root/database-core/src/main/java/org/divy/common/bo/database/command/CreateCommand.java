package org.divy.common.bo.database.command;

@FunctionalInterface
public interface CreateCommand<E> extends Command
{
    E create(E entity);
}