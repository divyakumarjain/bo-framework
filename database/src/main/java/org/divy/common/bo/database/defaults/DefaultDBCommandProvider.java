/**
 * 
 */
package org.divy.common.bo.database.defaults;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.database.TypeBaseDBCommandProvider;
import org.divy.common.bo.command.ICreateCommand;
import org.divy.common.bo.command.IDeleteCommand;
import org.divy.common.bo.command.IGetCommand;
import org.divy.common.bo.command.ISearchCommand;
import org.divy.common.bo.command.IUpdateCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Divyakumar
 *
 */
public class DefaultDBCommandProvider<E extends IBusinessObject<I>, I>
        extends
        TypeBaseDBCommandProvider<E, I> {

    private static final String COULD_NOT_CREATE_COMMAND = "Could not Create Command";
    private final Class<? extends E> entityClass;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public DefaultDBCommandProvider(String persistentUnitName,
                                    Class<E> entityClass) {
        //See below link for explaination of typecasing.
        //http://stackoverflow.com/questions/30090242/java-lang-class-generics-and-wildcards
        //http://stackoverflow.com/questions/26766704/cannot-convert-from-listlist-to-listlist

        super(persistentUnitName,
                (Class<? extends IGetCommand<E, I>>) (Class<? extends DefaultDatabaseGetCommand>) DefaultDatabaseGetCommand.class,
                (Class<? extends IUpdateCommand<E, I>>) (Class<? extends DefaultDatabaseUpdateCommand>) DefaultDatabaseUpdateCommand.class,
                (Class<? extends IDeleteCommand<E, I>>) (Class<? extends DefaultDatabaseDeleteCommand>) DefaultDatabaseDeleteCommand.class,
                (Class<? extends ICreateCommand<E>>) (Class<? extends DefaultDatabaseCreateCommand>) DefaultDatabaseCreateCommand.class,
                (Class<? extends ISearchCommand<E, I>>) (Class<? extends DefaultDatabaseSearchCommand>) DefaultDatabaseSearchCommand.class);

        this.entityClass = entityClass;
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

}
