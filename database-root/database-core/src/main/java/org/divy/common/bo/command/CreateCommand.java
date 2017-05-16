package org.divy.common.bo.command;

@FunctionalInterface
public interface CreateCommand<E> extends Command
{
    E create(E entity);
}