package org.divy.common.bo.command;

import org.divy.common.bo.IBusinessObject;


public abstract class AbstractDatabaseUpdateCommand<ENTITY extends IBusinessObject<ID>, ID>
		extends AbstractDatabaseCommand<ENTITY, ID> implements
		IUpdateCommand<ENTITY, ID>
{
	protected AbstractDatabaseUpdateCommand(
			Class<? extends ENTITY> typeParameterClass,IDBCommandContext context)
	{
		super(typeParameterClass);
		this.setContext(context);
	}

	protected abstract void copyFields(ENTITY source, ENTITY target);
	
	@Override
	public ENTITY update(ENTITY entity)
	{
		
		return doUpdate(entity);
	}

	/**
	 * @param entity
	 * @return
	 */
	private ENTITY doUpdate(ENTITY entity) {

		transactionBegin();

		ENTITY fromPersistance = getEntityManager().getReference(
				entityClass, entity.identity());
		
		copyFields(entity, fromPersistance);
		
		getEntityManager().persist(fromPersistance);
		
		transactionCommit();

		return fromPersistance;
	}
}
