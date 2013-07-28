/**
 * 
 */
package org.divy.common.bo.command.db.defaults;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.command.db.AbstractDatabaseUpdateCommand;
import org.divy.common.bo.command.db.IDBCommandContext;
import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.defaults.DefaultBOMapper;

/**
 * @author Divyakumar
 *
 */
public class DefaultDatabaseUpdateCommand<ENTITY extends IBusinessObject<ID>, ID>
		extends
		AbstractDatabaseUpdateCommand<ENTITY, ID> {

	/**
	 * @param typeParameterClass
	 * @param context
	 */
	public DefaultDatabaseUpdateCommand(
			Class<? extends ENTITY> typeParameterClass,
			IDBCommandContext context) {
		super(typeParameterClass, context);
		
		mapper = new DefaultBOMapper(typeParameterClass,typeParameterClass);
	}
	
	IBOMapper<ENTITY,ENTITY> mapper;
	
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.divy.common.bo.command.AbstractDatabaseUpdateCommand#copyFields(org
	 * .divy.common.bo.IBusinessObject, org.divy.common.bo.IBusinessObject)
	 */
	@Override
	protected void merge(ENTITY source, ENTITY target) {
		if(source != target)
			mapper.mapToBO(source, target);
	}
}
