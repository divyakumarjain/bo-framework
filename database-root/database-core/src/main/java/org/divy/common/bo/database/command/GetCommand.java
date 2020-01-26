package org.divy.common.bo.database.command;

import java.util.Optional;

@FunctionalInterface
public interface GetCommand<E, I> extends Command
{
    Optional<E> get(I identity);
}
