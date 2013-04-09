package org.divy.common.bo;

import java.lang.reflect.InvocationTargetException;

import org.divy.common.bo.command.ICreateCommand;
import org.divy.common.bo.command.IDBCommandContext;
import org.divy.common.bo.command.IDeleteCommand;
import org.divy.common.bo.command.IGetCommand;
import org.divy.common.bo.command.ISearchCommand;
import org.divy.common.bo.command.IUpdateCommand;
import org.divy.common.bo.context.DatabaseContext;

public class TypeBaseDBCommandProvider<ENTITY extends IBusinessObject<ID>, ID>
		implements ICommandProvider<ENTITY, ID>
{
	
	private IDBCommandContext context;

	private Class<? extends IGetCommand<ENTITY, ID>> getCommandType;
	private Class<? extends IUpdateCommand<ENTITY, ID>> updateCommandType;
	private Class<? extends IDeleteCommand<ENTITY, ID>> deleteCommandType;
	private Class<? extends ICreateCommand<ENTITY, ID>> createCommandType;
	private Class<? extends ISearchCommand<ENTITY, ID>> searchCommandType;
	
	public TypeBaseDBCommandProvider(String persistantUnitName)
	{ 
		context = new DatabaseContext(persistantUnitName);
	}

	public IDBCommandContext getContext() {
		return context;
	}

	public void setContext(final IDBCommandContext context) {
		this.context = context;
	}
	
	public Class<? extends IGetCommand<ENTITY, ID>> getGetCommandType() {
		return getCommandType;
	}

	public Class<? extends IUpdateCommand<ENTITY, ID>> getUpdateCommandType() {
		return updateCommandType;
	}

	public Class<? extends IDeleteCommand<ENTITY, ID>> getDeleteCommandType() {
		return deleteCommandType;
	}

	public Class<? extends ICreateCommand<ENTITY, ID>> getCreateCommandType() {
		return createCommandType;
	}

	public Class<? extends ISearchCommand<ENTITY, ID>> getSearchCommandType() {
		return searchCommandType;
	}

	public void setGetCommandType(final Class<? extends IGetCommand<ENTITY, ID>> getCommandType) {
		this.getCommandType = getCommandType;
	}

	public void setUpdateCommandType(final Class<? extends IUpdateCommand<ENTITY, ID>> updateCommandType) {
		this.updateCommandType = updateCommandType;
	}

	public void setDeleteCommandType(final Class<? extends IDeleteCommand<ENTITY, ID>> deleteCommandType) {
		this.deleteCommandType = deleteCommandType;
	}

	public void setCreateCommandType(final Class<? extends ICreateCommand<ENTITY, ID>> createCommandType) {
		this.createCommandType = createCommandType;
	}

	public void setSearchCommandType(
			final Class<? extends ISearchCommand<ENTITY, ID>> searchCommandType) {
		this.searchCommandType = searchCommandType;
	}

	protected Object createCommand(Class<?> type, IDBCommandContext context)
	{
		try
		{
			if(type==null)
				throw new IllegalArgumentException("Command type not provided");
		
			final Object returnInstance = type.getConstructor(
					IDBCommandContext.class).newInstance(context);

			return returnInstance;
			
		} catch (InstantiationException e)
		{
			throw new IllegalArgumentException("Could not Create Command",e);
		} catch (IllegalAccessException e)
		{
			throw new IllegalArgumentException("Could not Create Command",e);
		} catch (IllegalArgumentException e)
		{
			throw new IllegalArgumentException("Could not Create Command",e);
		} catch (SecurityException e)
		{
			throw new IllegalArgumentException("Could not Create Command",e);
		} catch (InvocationTargetException e)
		{
			throw new IllegalArgumentException("Could not Create Command",e);
		} catch (NoSuchMethodException e)
		{
			throw new IllegalArgumentException("Could not Create Command",e);
		}
	}

	private IDBCommandContext createContext()
	{
		return (IDBCommandContext) this.context.createChildContext();
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public IGetCommand<ENTITY, ID> getGetCommand()
	{
		final IDBCommandContext context = createContext();
		
		IGetCommand<ENTITY, ID> returnGetCommand;
		
		returnGetCommand = (IGetCommand) createCommand(getCommandType,context);
		
		return  returnGetCommand;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ICreateCommand<ENTITY, ID> getCreateCommand()
	{
		final IDBCommandContext context = createContext();
		
		ICreateCommand<ENTITY, ID> returnCreateCommand;
		
		returnCreateCommand =  (ICreateCommand<ENTITY,ID>)createCommand(createCommandType,context);
		
		return returnCreateCommand;
	}

	@Override
	@SuppressWarnings("unchecked")
	public IDeleteCommand<ENTITY, ID> getDeleteCommand()
	{
		final IDBCommandContext context = createContext();
		
		IDeleteCommand<ENTITY, ID> returnDeleteCommand;
		
		returnDeleteCommand = (IDeleteCommand<ENTITY, ID>)createCommand(deleteCommandType,context);
		
		return returnDeleteCommand;
	}

	@Override
	@SuppressWarnings("unchecked")
	public IUpdateCommand<ENTITY, ID> getUpdateCommand()
	{
		final IDBCommandContext context = createContext();
		
		IUpdateCommand<ENTITY, ID> returnUpdateCommand;
		
		returnUpdateCommand = (IUpdateCommand<ENTITY, ID>)createCommand(updateCommandType,context);
		
		return returnUpdateCommand;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.divy.common.bo.ICommandProvider#getSearchCommand()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ISearchCommand<ENTITY, ID> getSearchCommand() {

		final IDBCommandContext context = createContext();

		ISearchCommand<ENTITY, ID> returnSearchCommand;

		returnSearchCommand = (ISearchCommand<ENTITY, ID>) createCommand(
				searchCommandType, context);

		return returnSearchCommand;
	}
}
