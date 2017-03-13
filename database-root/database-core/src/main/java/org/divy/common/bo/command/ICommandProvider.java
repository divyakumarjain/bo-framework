package org.divy.common.bo.command;


@Deprecated
public interface ICommandProvider<E, I>
{

    IGetCommand<E, I> getGetCommand();

    ICreateCommand<E> getCreateCommand();

    IDeleteCommand<E, I> getDeleteCommand();

    IUpdateCommand<E, I> getUpdateCommand();

    ISearchCommand<E> getSearchCommand();
}