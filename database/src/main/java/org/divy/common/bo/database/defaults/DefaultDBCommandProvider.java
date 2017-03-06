package org.divy.common.bo.database.defaults;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.command.*;
import org.divy.common.bo.database.TypeBaseDBCommandProvider;
import org.divy.common.bo.database.context.EntityManagerCommandContext;
import org.divy.common.bo.mapper.IBOMapper;

import java.lang.reflect.InvocationTargetException;

@Deprecated
public class DefaultDBCommandProvider<E extends IBusinessObject<I>, I>
        extends
        TypeBaseDBCommandProvider<E, I> {

    private static final String COULD_NOT_CREATE_COMMAND = "Could not Create Command";


    @SuppressWarnings({"unchecked", "rawtypes"})
    public DefaultDBCommandProvider(String persistentUnitName,
                                    Class<E> entityClass,
                                    IBOMapper<E, E> updateMapper) {
        //See below link for explanation of typecasting.
        //http://stackoverflow.com/questions/30090242/java-lang-class-generics-and-wildcards
        //http://stackoverflow.com/questions/26766704/cannot-convert-from-listlist-to-listlist

        super(persistentUnitName
                , entityClass
                , (Class<? extends IGetCommand<E, I>>) (Class<?>)DefaultDatabaseGetCommand.class
                , (Class<? extends IUpdateCommand<E, I>>) (Class<?>)DefaultDatabaseUpdateCommand.class
                , (Class<? extends IDeleteCommand<E, I>>) (Class<?>)DefaultDatabaseDeleteCommand.class
                , (Class<? extends ICreateCommand<E>>) (Class<?>)DefaultDatabaseCreateCommand.class
                , (Class<? extends ISearchCommand<E, I>>) (Class<?>)DefaultDatabaseSearchCommand.class
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
            return updateCommandType.getConstructor(Class.class
                    , EntityManagerCommandContext.class
                    , IBOMapper.class).newInstance(entityClass, newContext, updateMapper);


        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            throw new IllegalArgumentException(COULD_NOT_CREATE_COMMAND, e);
        }
    }
}
