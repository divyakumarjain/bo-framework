package org.divy.common.bo.context;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.divy.common.bo.command.IDBCommandContext;

public class DatabaseContext implements IDBCommandContext
{

	private String persistanceUnitName;
	
	private ICommandContext parentContext;
	
	private EntityTransaction transaction;
	
	private EntityManager entityManager;

	protected String getPersistanceUnitName()
	{
		return persistanceUnitName;
	}

	protected void setPersistanceUnitName(String persistanceUnitName)
	{
		this.persistanceUnitName = persistanceUnitName;
	}

	public DatabaseContext(String persistanceUnitName)
	{
		this.persistanceUnitName = persistanceUnitName;
	}

	@Override
	public EntityManager getEntityManager()
	{
		if(entityManager==null)
		{
			if (parentContext instanceof DatabaseContext)
			{
				DatabaseContext dbParentContext = (DatabaseContext) parentContext;
				entityManager = dbParentContext.getEntityManager();
				
				if (entityManager != null) {
					return entityManager; 
				}
			}
			
			entityManager = Persistence.createEntityManagerFactory(persistanceUnitName).createEntityManager();
		}
		return entityManager;
	}

	@Override
	public ICommandContext getParentContext()
	{
		return parentContext;
	}


	@Override
	public ICommandContext createChildContext()
	{
		DatabaseContext childContext = new DatabaseContext(getPersistanceUnitName());
		
		childContext.parentContext = this;
		
		return childContext;
	}

	@Override
	public void commit()
	{
			transaction.commit();
	}
	
	@Override
	public void begin()
	{
		transaction = getEntityManager().getTransaction();
		transaction.begin();
	}
}
