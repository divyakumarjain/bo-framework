package org.divy.common.bo.command;

/**
 * Provided commands for persistence operation
 * @param <E> Entity for which persistence operation are needed
 * @param <I> Identity of the entity
 *
 * @deprecated Please use Dependency injection framework
 */
@Deprecated
public interface ICommandProvider<E, I>
{

    IGetCommand<E, I> getGetCommand();

    ICreateCommand<E> getCreateCommand();

    IDeleteCommand<E, I> getDeleteCommand();

    IUpdateCommand<E, I> getUpdateCommand();

    ISearchCommand<E> getSearchCommand();
}