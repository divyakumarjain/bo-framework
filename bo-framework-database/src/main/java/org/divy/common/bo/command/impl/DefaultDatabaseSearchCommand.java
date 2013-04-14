/**
 * 
 */
package org.divy.common.bo.command.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.IQuery;
import org.divy.common.bo.command.AbstractDatabaseSearchCommand;
import org.divy.common.bo.command.IDBCommandContext;

/**
 * @author divyakumar.a.jain@hp.com
 *
 */
public class DefaultDatabaseSearchCommand<ENTITY extends IBusinessObject<ID>, ID>
		extends
		AbstractDatabaseSearchCommand<ENTITY, ID> {

	/**
	 * @param entityClass
	 * @param context
	 */
	public DefaultDatabaseSearchCommand(Class<? extends ENTITY> entityClass,
			IDBCommandContext context) {
		super(entityClass, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.divy.common.bo.command.AbstractDatabaseSearchCommand#createCriteriaQuery
	 * (org.divy.common.bo.IQuery)
	 */
	@Override
	protected CriteriaQuery<? extends ENTITY> createCriteriaQuery(
			IQuery<ENTITY> query) {

		CriteriaQuery<? extends ENTITY> criteriaQuery = null;

		CriteriaBuilder criteriaBuilder = getEntityManager()
				.getCriteriaBuilder();

		criteriaQuery = criteriaBuilder.createQuery(getEntityType());

		criteriaQuery.from(getEntityType());

		return criteriaQuery;
	}

}
