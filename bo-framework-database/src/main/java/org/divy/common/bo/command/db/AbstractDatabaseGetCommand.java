package org.divy.common.bo.command.db;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.command.IGetCommand;

public abstract class AbstractDatabaseGetCommand<ENTITY extends IBusinessObject<ID>, ID>
		extends AbstractDatabaseCommand<ENTITY, ID> implements
		IGetCommand<ENTITY, ID>
{
	protected AbstractDatabaseGetCommand(
			Class<? extends ENTITY> typeParameterClass, IDBCommandContext context)
	{
		super(typeParameterClass);
		this.setContext(context);
	}

	@Override
	public ENTITY get(ID id)
	{
		
		ENTITY entity = getEntityManager().find(getEntityType(), id);
		
		return entity;
	}
}
