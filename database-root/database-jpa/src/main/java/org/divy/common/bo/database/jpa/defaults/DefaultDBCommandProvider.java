package org.divy.common.bo.database.jpa.defaults;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.command.*;
import org.divy.common.bo.database.jpa.EntityManagerCommandContext;
import org.divy.common.bo.database.jpa.TypeBaseDBCommandProvider;
import org.divy.common.bo.mapper.BOMapper;

import java.lang.reflect.InvocationTargetException;

@Deprecated
public class DefaultDBCommandProvider<E extends BusinessObject<I>, I>
        extends
        TypeBaseDBCommandProvider<E, I> {

    private static final String COULD_NOT_CREATE_COMMAND = "Could not Create Command";


    @SuppressWarnings({"unchecked", "rawtypes"})
    public DefaultDBCommandProvider(EntityManagerCommandContext context,
                                    Class<E> entityClass,
                                    BOMapper<E, E> updateMapper) {
        //See below link for explanation of typecasting.
        //http://stackoverflow.com/questions/30090242/java-lang-class-generics-and-wildcards
        //http://stackoverflow.com/questions/26766704/cannot-convert-from-listlist-to-listlist

        super(context
                , entityClass
                , (Class<? extends GetCommand<E, I>>) (Class<?>)DefaultDatabaseGetCommand.class
                , (Class<? extends UpdateCommand<E, I>>) (Class<?>)DefaultDatabaseUpdateCommand.class
                , (Class<? extends DeleteCommand<E, I>>) (Class<?>)DefaultDatabaseDeleteCommand.class
                , (Class<? extends CreateCommand<E>>) (Class<?>)DefaultDatabaseCreateCommand.class
                , (Class<? extends SearchCommand<E>>) (Class<?>)DefaultDatabaseSearchCommand.class
                , updateMapper);
    }

    @Override
    protected Object createCommand(Class<?> type, EntityManagerCommandContext context) {
        try {
            if (type == null) {
                throw new IllegalArgumentException("Command _type not provided");
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
    protected Object createCommand(Class<? extends UpdateCommand<E, I>> updateCommandType, EntityManagerCommandContext newContext, BOMapper<E, E> updateMapper) {
        try {
            if (updateCommandType == null) {
                throw new IllegalArgumentException("Command _type not provided");
            }
            return updateCommandType.getConstructor(Class.class
                    , EntityManagerCommandContext.class
                    , BOMapper.class).newInstance(entityClass, newContext, updateMapper);


        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            throw new IllegalArgumentException(COULD_NOT_CREATE_COMMAND, e);
        }
    }
}
