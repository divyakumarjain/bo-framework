/**
 * 
 */
package org.divy.common.bo.database.mock;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.divy.common.bo.IQuery;
import org.divy.common.bo.command.AbstractDatabaseSearchCommand;
import org.divy.common.bo.command.IDBCommandContext;

/**
 * @author divyakumar.a.jain@hp.com
 *
 */
public class MockSearchCommand extends
		AbstractDatabaseSearchCommand<MockEntity, String> {

	/**
	 * @param entityClass
	 * @param context
	 */
	public MockSearchCommand(IDBCommandContext context) {
		super(MockEntity.class, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.divy.common.bo.command.AbstractDatabaseSearchCommand#createCriteriaQuery
	 * (org.divy.common.bo.IQuery)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected CriteriaQuery<? extends MockEntity> createCriteriaQuery(
			IQuery<MockEntity> query) {
		CriteriaQuery<MockEntity> taskQuery = null;

		CriteriaBuilder criteriaBuilder = getEntityManager()
				.getCriteriaBuilder();

		taskQuery = (CriteriaQuery<MockEntity>) criteriaBuilder
				.createQuery(getEntityType());

		taskQuery.from(getEntityType());

		return taskQuery;
	}

}
