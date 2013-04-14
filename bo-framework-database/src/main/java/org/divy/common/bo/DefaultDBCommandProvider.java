/**
 * 
 */
package org.divy.common.bo;

import java.lang.reflect.InvocationTargetException;

import org.divy.common.bo.command.ICreateCommand;
import org.divy.common.bo.command.IDBCommandContext;
import org.divy.common.bo.command.IDeleteCommand;
import org.divy.common.bo.command.IGetCommand;
import org.divy.common.bo.command.ISearchCommand;
import org.divy.common.bo.command.IUpdateCommand;
import org.divy.common.bo.command.impl.DefaultDatabaseCreateCommand;
import org.divy.common.bo.command.impl.DefaultDatabaseDeleteCommand;
import org.divy.common.bo.command.impl.DefaultDatabaseGetCommand;
import org.divy.common.bo.command.impl.DefaultDatabaseSearchCommand;
import org.divy.common.bo.command.impl.DefaultDatabaseUpdateCommand;

/**
 * @author divyakumar.a.jain@hp.com
 *
 */
public class DefaultDBCommandProvider<ENTITY extends IBusinessObject<ID>, ID>
		extends
		TypeBaseDBCommandProvider<ENTITY, ID> {

	/**
	 * 
	 */
	private static final String COULD_NOT_CREATE_COMMAND = "Could not Create Command";

	/**
	 * @param persistantUnitName
	 */
	@SuppressWarnings("unchecked")
	public DefaultDBCommandProvider(String persistantUnitName,
			Class<ENTITY> entityClass) {
		super(persistantUnitName);

		setGetCommandType((Class<? extends IGetCommand<ENTITY, ID>>) (Object)DefaultDatabaseGetCommand.class);
		setCreateCommandType((Class<? extends ICreateCommand<ENTITY, ID>>) (Object)DefaultDatabaseCreateCommand.class);
		setDeleteCommandType((Class<? extends IDeleteCommand<ENTITY, ID>>) (Object)DefaultDatabaseDeleteCommand.class);
		setSearchCommandType((Class<? extends ISearchCommand<ENTITY, ID>>) (Object)DefaultDatabaseSearchCommand.class);
		setUpdateCommandType((Class<? extends IUpdateCommand<ENTITY, ID>>) (Object)DefaultDatabaseUpdateCommand.class);

		this.entityClass = entityClass;
	}

	Class<? extends ENTITY> entityClass;

	@Override
	protected Object createCommand(Class<?> type, IDBCommandContext context) {
		try {
			if (type == null)
				throw new IllegalArgumentException("Command type not provided");

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
