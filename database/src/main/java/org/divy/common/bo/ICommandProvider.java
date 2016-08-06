package org.divy.common.bo;

import org.divy.common.bo.command.*;

public interface ICommandProvider<E, I>
{

    IGetCommand<E, I> getGetCommand();

    ICreateCommand<E, I> getCreateCommand();

    IDeleteCommand<E, I> getDeleteCommand();

    IUpdateCommand<E, I> getUpdateCommand();

    ISearchCommand<E, I> getSearchCommand();
}