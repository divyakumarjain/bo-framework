/**
 * 
 */
package org.divy.common.bo.command.impl;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.command.AbstractDatabaseCreateCommand;
import org.divy.common.bo.command.IDBCommandContext;

/**
 * @author divyakumar.a.jain@hp.com
 *
 */
public class DefaultDatabaseCreateCommand<ENTITY extends IBusinessObject<ID>, ID>
		extends
		AbstractDatabaseCreateCommand<ENTITY, ID> {

	/**
	 * @param entityClass
	 * @param context
	 */
	public DefaultDatabaseCreateCommand(Class<? extends ENTITY> entityClass,
			IDBCommandContext context) {
		super(entityClass, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.divy.common.bo.command.ICreateCommand#create(java.lang.Object)
	 */
	@Override
	public ENTITY create(ENTITY entity) {

		persist(entity);
		return entity;
	}

}
