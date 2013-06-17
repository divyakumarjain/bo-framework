/**
 * 
 */
package org.divy.common.bo.command.impl;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.command.AbstractDatabaseGetCommand;
import org.divy.common.bo.command.IDBCommandContext;

/**
 * @author Divyakumar
 *
 */
public class DefaultDatabaseGetCommand<ENTITY extends IBusinessObject<ID>, ID>
		extends AbstractDatabaseGetCommand<ENTITY, ID> {

	/**
	 * @param entityClass
	 * @param context
	 */
	public DefaultDatabaseGetCommand(
			Class<? extends ENTITY> entityClass,
			IDBCommandContext context) {
		super(entityClass, context);
	}

}
