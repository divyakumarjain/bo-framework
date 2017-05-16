package org.divy.common.bo.command;

@FunctionalInterface
public interface GetCommand<E, I> extends Command
{
    E get(I identity);

}