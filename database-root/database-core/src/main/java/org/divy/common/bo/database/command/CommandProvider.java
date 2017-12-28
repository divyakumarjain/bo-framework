package org.divy.common.bo.database.command;

/**
 * Provided commands for persistence operation
 * @param <E> Entity for which persistence operation are needed
 * @param <I> Identity of the entity
 *
 * @deprecated Please use Dependency injection framework
 */
@Deprecated
public interface CommandProvider<E, I>
{

    GetCommand<E, I> getGetCommand();

    CreateCommand<E> getCreateCommand();

    DeleteCommand<E, I> getDeleteCommand();

    UpdateCommand<E, I> getUpdateCommand();

    SearchCommand<E> getSearchCommand();
}