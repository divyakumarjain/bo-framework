package org.divy.common.bo.database.defaults;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.database.TypeBaseDBCommandProvider;
import org.divy.common.bo.command.ICreateCommand;
import org.divy.common.bo.command.IDeleteCommand;
import org.divy.common.bo.command.IGetCommand;
import org.divy.common.bo.command.ISearchCommand;
import org.divy.common.bo.command.IUpdateCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;
import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.builder.MapperBuilder;

import java.lang.reflect.InvocationTargetException;

public class DefaultDBCommandProvider<E extends IBusinessObject<I>, I>
        extends
        TypeBaseDBCommandProvider<E, I> {

    private static final String COULD_NOT_CREATE_COMMAND = "Could not Create Command";

    @SuppressWarnings({"unchecked", "rawtypes"})
    public DefaultDBCommandProvider(String persistentUnitName,
                                    Class<E> entityClass,
                                    MapperBuilder mapperBuilder) {
        //See below link for explanation of typecasting.
        //http://stackoverflow.com/questions/30090242/java-lang-class-generics-and-wildcards
        //http://stackoverflow.com/questions/26766704/cannot-convert-from-listlist-to-listlist

        super(persistentUnitName
                , entityClass
                , (Class<? extends IGetCommand<E, I>>) (Class<? extends DefaultDatabaseGetCommand>) DefaultDatabaseGetCommand.class
                , (Class<? extends IUpdateCommand<E, I>>) (Class<? extends DefaultDatabaseUpdateCommand>) DefaultDatabaseUpdateCommand.class
                , (Class<? extends IDeleteCommand<E, I>>) (Class<? extends DefaultDatabaseDeleteCommand>) DefaultDatabaseDeleteCommand.class
                , (Class<? extends ICreateCommand<E>>) (Class<? extends DefaultDatabaseCreateCommand>) DefaultDatabaseCreateCommand.class
                , (Class<? extends ISearchCommand<E, I>>) (Class<? extends DefaultDatabaseSearchCommand>) DefaultDatabaseSearchCommand.class
                , mapperBuilder);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public DefaultDBCommandProvider(String persistentUnitName,
                                    Class<E> entityClass,
                                    IBOMapper<E, E> updateMapper) {
        //See below link for explanation of typecasting.
        //http://stackoverflow.com/questions/30090242/java-lang-class-generics-and-wildcards
        //http://stackoverflow.com/questions/26766704/cannot-convert-from-listlist-to-listlist

        super(persistentUnitName
                , entityClass
                , (Class<? extends IGetCommand<E, I>>) (Class<? extends DefaultDatabaseGetCommand>) DefaultDatabaseGetCommand.class
                , (Class<? extends IUpdateCommand<E, I>>) (Class<? extends DefaultDatabaseUpdateCommand>) DefaultDatabaseUpdateCommand.class
                , (Class<? extends IDeleteCommand<E, I>>) (Class<? extends DefaultDatabaseDeleteCommand>) DefaultDatabaseDeleteCommand.class
                , (Class<? extends ICreateCommand<E>>) (Class<? extends DefaultDatabaseCreateCommand>) DefaultDatabaseCreateCommand.class
                , (Class<? extends ISearchCommand<E, I>>) (Class<? extends DefaultDatabaseSearchCommand>) DefaultDatabaseSearchCommand.class
                , updateMapper);
    }

    @Override
    protected Object createCommand(Class<?> type, EntityManagerCommandContext context) {
        try {
            if (type == null) {
                throw new IllegalArgumentException("Command type not provided");
            }
            return type.getConstructor(Class.class,
                    EntityManagerCommandContext.class).newInstance(entityClass, context);


        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            throw new IllegalArgumentException(COULD_NOT_CREATE_COMMAND, e);
        }
    }

    @Override
    protected Object createCommand(Class<? extends IUpdateCommand<E, I>> updateCommandType, EntityManagerCommandContext newContext, IBOMapper<E, E> updateMapper) {
        try {
            if (updateCommandType == null) {
                throw new IllegalArgumentException("Command type not provided");
            }
            return updateCommandType.getConstructor(Class.class,
                    EntityManagerCommandContext.class).newInstance(entityClass, newContext, updateCommandType);


        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            throw new IllegalArgumentException(COULD_NOT_CREATE_COMMAND, e);
        }
    }
}
