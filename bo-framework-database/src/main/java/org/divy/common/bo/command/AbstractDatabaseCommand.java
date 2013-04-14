package org.divy.common.bo.command;

import javax.persistence.EntityManager;

import org.divy.common.bo.IBusinessObject;

public abstract class AbstractDatabaseCommand<ENTITY extends IBusinessObject<ID>, ID>
{

	private IDBCommandContext context;
	
	protected final Class<? extends ENTITY> entityClass;
	
	
	protected void setContext(IDBCommandContext context)
	{
		this.context = context;
	}
	
	protected AbstractDatabaseCommand(Class<? extends ENTITY> typeParameterClass)
	{
		this.entityClass = typeParameterClass;
	}

	protected final EntityManager getEntityManager()
	{
		return context.getEntityManager();
	}


	protected void transactionCommit()
	{
		context.commit();
	}

	protected void transactionBegin()
	{
		context.begin();
	}
	
	protected ENTITY getReference(Object identity)
	{
		
		ENTITY entity = getEntityManager().find(entityClass, identity);
		
		return entity;
	}
	
	protected ENTITY find(Object identity)
	{
		ENTITY entity = getEntityManager().find(entityClass, identity);
		
		return entity;
	}
}
