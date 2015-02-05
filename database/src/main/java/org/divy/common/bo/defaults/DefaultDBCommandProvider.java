/**
 * 
 */
package org.divy.common.bo.defaults;

import java.lang.reflect.InvocationTargetException;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.TypeBaseDBCommandProvider;
import org.divy.common.bo.command.ICreateCommand;
import org.divy.common.bo.command.IDeleteCommand;
import org.divy.common.bo.command.IGetCommand;
import org.divy.common.bo.command.ISearchCommand;
import org.divy.common.bo.command.IUpdateCommand;
import org.divy.common.bo.IDBCommandContext;

/**
 * @author Divyakumar
 *
 */
public class DefaultDBCommandProvider<ENTITY extends IBusinessObject<ID>, ID>
        extends
        TypeBaseDBCommandProvider<ENTITY, ID> {

    private static final String COULD_NOT_CREATE_COMMAND = "Could not Create Command";

    @SuppressWarnings("unchecked")
    public DefaultDBCommandProvider(String persistentUnitName,
            Class<ENTITY> entityClass) {
        super(persistentUnitName);

        setGetCommandType((Class<? extends IGetCommand<ENTITY, ID>>)(Object) DefaultDatabaseGetCommand.class);
        setCreateCommandType((Class<? extends ICreateCommand<ENTITY, ID>>)(Object) DefaultDatabaseCreateCommand.class);
        setDeleteCommandType((Class<? extends IDeleteCommand<ENTITY, ID>>)(Object) DefaultDatabaseDeleteCommand.class);
        setSearchCommandType((Class<? extends ISearchCommand<ENTITY, ID>>)(Object) DefaultDatabaseSearchCommand.class);
        setUpdateCommandType((Class<? extends IUpdateCommand<ENTITY, ID>>)(Object) DefaultDatabaseUpdateCommand.class);

        this.entityClass = entityClass;
    }

    private final Class<? extends ENTITY> entityClass;

    @Override
    protected Object createCommand(Class<?> type, IDBCommandContext context) {
        try {
            if (type == null) {
                throw new IllegalArgumentException("Command type not provided");
            }

            final Object returnInstance = type.getConstructor(Class.class,
                    IDBCommandContext.class).newInstance(entityClass, context);

            return returnInstance;

        } catch (InstantiationException e) {
            throw new IllegalArgumentException(COULD_NOT_CREATE_COMMAND, e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(COULD_NOT_CREATE_COMMAND, e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(COULD_NOT_CREATE_COMMAND, e);
        } catch (SecurityException e) {
            throw new IllegalArgumentException(COULD_NOT_CREATE_COMMAND, e);
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException(COULD_NOT_CREATE_COMMAND, e);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(COULD_NOT_CREATE_COMMAND, e);
        }
    }

}
