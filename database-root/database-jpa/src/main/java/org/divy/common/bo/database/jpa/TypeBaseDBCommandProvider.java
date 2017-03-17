package org.divy.common.bo.database.jpa;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.command.*;
import org.divy.common.bo.context.HierarchicalCommandContext;
import org.divy.common.bo.mapper.IBOMapper;

import java.lang.reflect.InvocationTargetException;

/**
 *@deprecated Use Dependency injection instead of CommandProvider for Command Injection
 */
@Deprecated
public class TypeBaseDBCommandProvider<E extends IBusinessObject<I>, I>
        implements ICommandProvider<E, I>
{

    private static final String COULD_NOT_CREATE_COMMAND = "Could not Create Command";

    private EntityManagerCommandContext context;
    protected final Class<? extends E> entityClass;

    private Class<? extends IGetCommand<E, I>> getCommandType;
    private Class<? extends IUpdateCommand<E, I>> updateCommandType;
    private Class<? extends IDeleteCommand<E, I>> deleteCommandType;
    private Class<? extends ICreateCommand<E>> createCommandType;
    private Class<? extends ISearchCommand<E>> searchCommandType;

    private final IBOMapper<E, E> updateMapper;

    public TypeBaseDBCommandProvider(EntityManagerCommandContext context,
                                     Class<E> entityClass,
                                     Class<? extends IGetCommand<E, I>> getCommandType,
                                     Class<? extends IUpdateCommand<E, I>> updateCommandType,
                                     Class<? extends IDeleteCommand<E, I>> deleteCommandType,
                                     Class<? extends ICreateCommand<E>> createCommandType,
                                     Class<? extends ISearchCommand<E>> searchCommandType,
                                     IBOMapper<E, E> updateMapper) {

        this.context = context;

        this.entityClass = entityClass;

        this.getCommandType = getCommandType;
        this.updateCommandType = updateCommandType;
        this.deleteCommandType = deleteCommandType;
        this.createCommandType = createCommandType;
        this.searchCommandType = searchCommandType;
        this.updateMapper = updateMapper;
    }


    public EntityManagerCommandContext getContext() {
        return context;
    }

    public void setContext(final EntityManagerCommandContext context) {
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

    public Class<? extends ISearchCommand<E>> getSearchCommandType() {
        return searchCommandType;
    }

    public void setSearchCommandType(
            final Class<? extends ISearchCommand<E>> searchCommandType) {
        this.searchCommandType = searchCommandType;
    }

    protected Object createCommand(Class<?> type, EntityManagerCommandContext context)
    {
        try
        {
            if (type == null) {
                throw new IllegalArgumentException("Command type not provided");
            }

            return type.getConstructor(
                    EntityManagerCommandContext.class).newInstance(context);



        } catch (InstantiationException |IllegalAccessException | IllegalArgumentException
        |SecurityException |InvocationTargetException |NoSuchMethodException e) {
            throw new IllegalArgumentException(COULD_NOT_CREATE_COMMAND,e);
        }
    }

    protected Object createCommand(Class<? extends IUpdateCommand<E, I>> updateCommandType
            , EntityManagerCommandContext newContext
            , IBOMapper<E, E> updateMapper) {
        try
        {
            if (updateCommandType == null) {
                throw new IllegalArgumentException("Command type not provided");
            }
            return updateCommandType.getConstructor(EntityManagerCommandContext.class
                    , IBOMapper.class)
                    .newInstance(newContext, updateMapper);

        } catch (InstantiationException |IllegalAccessException | IllegalArgumentException
                |SecurityException |InvocationTargetException |NoSuchMethodException e)
        {
            throw new IllegalArgumentException(COULD_NOT_CREATE_COMMAND,e);
        }
    }

    private EntityManagerCommandContext createContext()
    {
        if(context instanceof HierarchicalCommandContext){
            return (EntityManagerCommandContext) ((HierarchicalCommandContext)this.context).createChildContext();
        } else
            return context;

    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public IGetCommand<E, I> getGetCommand()
    {
        final EntityManagerCommandContext newContext = createContext();

        IGetCommand<E, I> returnGetCommand;

        returnGetCommand = (IGetCommand) createCommand(getCommandType,newContext);

        return  returnGetCommand;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ICreateCommand<E> getCreateCommand()
    {
        final EntityManagerCommandContext newContext = createContext();

        ICreateCommand<E> returnCreateCommand;

        returnCreateCommand = (ICreateCommand<E>) createCommand(createCommandType, newContext);

        return returnCreateCommand;
    }

    @Override
    @SuppressWarnings("unchecked")
    public IDeleteCommand<E, I> getDeleteCommand()
    {
        final EntityManagerCommandContext newContext = createContext();

        IDeleteCommand<E, I> returnDeleteCommand;

        returnDeleteCommand = (IDeleteCommand<E, I>) createCommand(deleteCommandType, newContext);

        return returnDeleteCommand;
    }

    @Override
    @SuppressWarnings("unchecked")
    public IUpdateCommand<E, I> getUpdateCommand()
    {
        final EntityManagerCommandContext newContext = createContext();

        IUpdateCommand<E, I> returnUpdateCommand;

        returnUpdateCommand = (IUpdateCommand<E, I>) createCommand(updateCommandType, newContext, updateMapper);

        return returnUpdateCommand;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.divy.common.bo.database.ICommandProvider#getSearchCommand()
     */
    @SuppressWarnings("unchecked")
    @Override
    public ISearchCommand<E> getSearchCommand() {

        final EntityManagerCommandContext newContext = createContext();

        ISearchCommand<E> returnSearchCommand;

        returnSearchCommand = (ISearchCommand<E>) createCommand(
                searchCommandType, newContext);

        return returnSearchCommand;
    }
}
