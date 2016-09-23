package org.divy.common.bo;

import org.divy.common.bo.command.*;
import org.divy.common.bo.context.DatabaseContext;

import java.lang.reflect.InvocationTargetException;

public class TypeBaseDBCommandProvider<E extends IBusinessObject<I>, I>
        implements ICommandProvider<E, I>
{

    private static final String COULD_NOT_CREATE_COMMAND = "Could not Create Command";

    private IDBCommandContext context;

    private Class<? extends IGetCommand<E, I>> getCommandType;
    private Class<? extends IUpdateCommand<E, I>> updateCommandType;
    private Class<? extends IDeleteCommand<E, I>> deleteCommandType;
    private Class<? extends ICreateCommand<E>> createCommandType;
    private Class<? extends ISearchCommand<E, I>> searchCommandType;

    public TypeBaseDBCommandProvider(String persistentUnitName,
                                     Class<? extends IGetCommand<E, I>> getCommandType,
                                     Class<? extends IUpdateCommand<E, I>> updateCommandType,
                                     Class<? extends IDeleteCommand<E, I>> deleteCommandType,
                                     Class<? extends ICreateCommand<E>> createCommandType,
                                     Class<? extends ISearchCommand<E, I>> searchCommandType) {

        context = new DatabaseContext(persistentUnitName);

        this.getCommandType = getCommandType;
        this.updateCommandType = updateCommandType;
        this.deleteCommandType = deleteCommandType;
        this.createCommandType = createCommandType;
        this.searchCommandType = searchCommandType;
    }


    public IDBCommandContext getContext() {
        return context;
    }

    public void setContext(final IDBCommandContext context) {
        this.context = context;
    }

    public Class<? extends IGetCommand<E, I>> getGetCommandType() {
        return getCommandType;
    }

    public void setGetCommandType(final Class<? extends IGetCommand<E, I>> getCommandType) {
        this.getCommandType = getCommandType;
    }

    public Class<? extends IUpdateCommand<E, I>> getUpdateCommandType() {
        return updateCommandType;
    }

    public void setUpdateCommandType(final Class<? extends IUpdateCommand<E, I>> updateCommandType) {
        this.updateCommandType = updateCommandType;
    }

    public Class<? extends IDeleteCommand<E, I>> getDeleteCommandType() {
        return deleteCommandType;
    }

    public void setDeleteCommandType(final Class<? extends IDeleteCommand<E, I>> deleteCommandType) {
        this.deleteCommandType = deleteCommandType;
    }

    public Class<? extends ICreateCommand<E>> getCreateCommandType() {
        return createCommandType;
    }

    public void setCreateCommandType(final Class<? extends ICreateCommand<E>> createCommandType) {
        this.createCommandType = createCommandType;
    }

    public Class<? extends ISearchCommand<E, I>> getSearchCommandType() {
        return searchCommandType;
    }

    public void setSearchCommandType(
            final Class<? extends ISearchCommand<E, I>> searchCommandType) {
        this.searchCommandType = searchCommandType;
    }

    protected Object createCommand(Class<?> type, IDBCommandContext context)
    {
        try
        {
            if (type == null) {
                throw new IllegalArgumentException("Command type not provided");
            }

            return type.getConstructor(
                    IDBCommandContext.class).newInstance(context);

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | SecurityException | InvocationTargetException | NoSuchMethodException e)
        {
            throw new IllegalArgumentException(COULD_NOT_CREATE_COMMAND,e);
        }
    }

    private IDBCommandContext createContext()
    {
        return (IDBCommandContext) this.context.createChildContext();
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public IGetCommand<E, I> getGetCommand()
    {
        final IDBCommandContext newContext = createContext();

        IGetCommand<E, I> returnGetCommand;

        returnGetCommand = (IGetCommand) createCommand(getCommandType,newContext);

        return  returnGetCommand;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ICreateCommand<E> getCreateCommand()
    {
        final IDBCommandContext newContext = createContext();

        ICreateCommand<E> returnCreateCommand;

        returnCreateCommand = (ICreateCommand<E>) createCommand(createCommandType, newContext);

        return returnCreateCommand;
    }

    @Override
    @SuppressWarnings("unchecked")
    public IDeleteCommand<E, I> getDeleteCommand()
    {
        final IDBCommandContext newContext = createContext();

        IDeleteCommand<E, I> returnDeleteCommand;

        returnDeleteCommand = (IDeleteCommand<E, I>) createCommand(deleteCommandType, newContext);

        return returnDeleteCommand;
    }

    @Override
    @SuppressWarnings("unchecked")
    public IUpdateCommand<E, I> getUpdateCommand()
    {
        final IDBCommandContext newContext = createContext();

        IUpdateCommand<E, I> returnUpdateCommand;

        returnUpdateCommand = (IUpdateCommand<E, I>) createCommand(updateCommandType, newContext);

        return returnUpdateCommand;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.divy.common.bo.ICommandProvider#getSearchCommand()
     */
    @SuppressWarnings("unchecked")
    @Override
    public ISearchCommand<E, I> getSearchCommand() {

        final IDBCommandContext newContext = createContext();

        ISearchCommand<E, I> returnSearchCommand;

        returnSearchCommand = (ISearchCommand<E, I>) createCommand(
                searchCommandType, newContext);

        return returnSearchCommand;
    }
}
