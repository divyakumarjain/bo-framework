package org.divy.common.bo.database.jpa;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.database.command.*;
import org.divy.common.bo.database.context.HierarchicalCommandContext;
import org.divy.common.bo.mapper.BOMapper;

import java.lang.reflect.InvocationTargetException;

/**
 */
public class TypeBaseDBCommandProvider<E extends BusinessObject<I>, I>
        implements CommandProvider<E, I>
{

    private static final String COULD_NOT_CREATE_COMMAND = "Could not Create Command";

    private EntityManagerCommandContext context;
    protected final Class<? extends E> entityClass;

    private Class<? extends GetCommand<E, I>> getCommandType;
    private Class<? extends UpdateCommand<E, I>> updateCommandType;
    private Class<? extends DeleteCommand<E, I>> deleteCommandType;
    private Class<? extends CreateCommand<E>> createCommandType;
    private Class<? extends SearchCommand<E>> searchCommandType;

    private final BOMapper<E, E> updateMapper;

    public TypeBaseDBCommandProvider(EntityManagerCommandContext context,
                                     Class<E> entityClass,
                                     Class<? extends GetCommand<E, I>> getCommandType,
                                     Class<? extends UpdateCommand<E, I>> updateCommandType,
                                     Class<? extends DeleteCommand<E, I>> deleteCommandType,
                                     Class<? extends CreateCommand<E>> createCommandType,
                                     Class<? extends SearchCommand<E>> searchCommandType,
                                     BOMapper<E, E> updateMapper) {

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

    public Class<? extends GetCommand<E, I>> getGetCommandType() {
        return getCommandType;
    }

    public void setGetCommandType(final Class<? extends GetCommand<E, I>> getCommandType) {
        this.getCommandType = getCommandType;
    }

    public Class<? extends UpdateCommand<E, I>> getUpdateCommandType() {
        return updateCommandType;
    }

    public void setUpdateCommandType(final Class<? extends UpdateCommand<E, I>> updateCommandType) {
        this.updateCommandType = updateCommandType;
    }

    public Class<? extends DeleteCommand<E, I>> getDeleteCommandType() {
        return deleteCommandType;
    }

    public void setDeleteCommandType(final Class<? extends DeleteCommand<E, I>> deleteCommandType) {
        this.deleteCommandType = deleteCommandType;
    }

    public Class<? extends CreateCommand<E>> getCreateCommandType() {
        return createCommandType;
    }

    public void setCreateCommandType(final Class<? extends CreateCommand<E>> createCommandType) {
        this.createCommandType = createCommandType;
    }

    public Class<? extends SearchCommand<E>> getSearchCommandType() {
        return searchCommandType;
    }

    public void setSearchCommandType(
            final Class<? extends SearchCommand<E>> searchCommandType) {
        this.searchCommandType = searchCommandType;
    }

    protected Object createCommand(Class<?> type, EntityManagerCommandContext context)
    {
        try
        {
            if (type == null) {
                throw new IllegalArgumentException("Command _type not provided");
            }

            return type.getConstructor(
                    EntityManagerCommandContext.class).newInstance(context);



        } catch (InstantiationException |IllegalAccessException | IllegalArgumentException
                 |SecurityException |InvocationTargetException |NoSuchMethodException e) {
            throw new IllegalArgumentException(COULD_NOT_CREATE_COMMAND,e);
        }
    }

    protected Object createCommand(Class<? extends UpdateCommand<E, I>> updateCommandType
            , EntityManagerCommandContext newContext
            , BOMapper<E, E> updateMapper) {
        try
        {
            if (updateCommandType == null) {
                throw new IllegalArgumentException("Command _type not provided");
            }
            return updateCommandType.getConstructor(EntityManagerCommandContext.class
                            , BOMapper.class)
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
    public GetCommand<E, I> getGetCommand()
    {
        final EntityManagerCommandContext newContext = createContext();

        GetCommand<E, I> returnGetCommand;

        returnGetCommand = (GetCommand) createCommand(getCommandType,newContext);

        return  returnGetCommand;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CreateCommand<E> getCreateCommand()
    {
        final EntityManagerCommandContext newContext = createContext();

        CreateCommand<E> returnCreateCommand;

        returnCreateCommand = (CreateCommand<E>) createCommand(createCommandType, newContext);

        return returnCreateCommand;
    }

    @Override
    @SuppressWarnings("unchecked")
    public DeleteCommand<E, I> getDeleteCommand()
    {
        final EntityManagerCommandContext newContext = createContext();

        DeleteCommand<E, I> returnDeleteCommand;

        returnDeleteCommand = (DeleteCommand<E, I>) createCommand(deleteCommandType, newContext);

        return returnDeleteCommand;
    }

    @Override
    @SuppressWarnings("unchecked")
    public UpdateCommand<E, I> getUpdateCommand()
    {
        final EntityManagerCommandContext newContext = createContext();

        UpdateCommand<E, I> returnUpdateCommand;

        returnUpdateCommand = (UpdateCommand<E, I>) createCommand(updateCommandType, newContext, updateMapper);

        return returnUpdateCommand;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.divy.common.bo.database.CommandProvider#getSearchCommand()
     */
    @SuppressWarnings("unchecked")
    @Override
    public SearchCommand<E> getSearchCommand() {

        final EntityManagerCommandContext newContext = createContext();

        SearchCommand<E> returnSearchCommand;

        returnSearchCommand = (SearchCommand<E>) createCommand(
                searchCommandType, newContext);

        return returnSearchCommand;
    }
}
