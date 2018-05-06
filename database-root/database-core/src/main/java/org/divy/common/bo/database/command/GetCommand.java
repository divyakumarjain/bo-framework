package org.divy.common.bo.database.command;

@FunctionalInterface
public interface GetCommand<E, I> extends Command
{
    E get(I identity);

}