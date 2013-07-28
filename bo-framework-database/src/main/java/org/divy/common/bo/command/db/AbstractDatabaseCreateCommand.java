package org.divy.common.bo.command.db;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.command.ICreateCommand;

public abstract class AbstractDatabaseCreateCommand<ENTITY extends IBusinessObject<ID>, ID>
		extends AbstractDatabaseCommand<ENTITY, ID> implements
		ICreateCommand<ENTITY, ID>
{
	protected AbstractDatabaseCreateCommand(
			Class<? extends ENTITY> typeParameterClass, IDBCommandContext context)
	{
		super(typeParameterClass);
		this.setContext(context);
	}

	protected void persist(ENTITY object)
	{
		doPersist(object);
	}

	/**
	 * @param object
	 */
	private void doPersist(ENTITY object) {
		transactionBegin();
		
		getEntityManager().persist(object);
		
		transactionCommit();
	}
	
	public ENTITY create(ENTITY object)
	{
		persist(object);
		return object;
	}
}
