package org.divy.common.bo.command;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.IQuery;


public abstract class AbstractDatabaseSearchCommand<ENTITY extends IBusinessObject<ID>, ID>
		extends
		AbstractDatabaseCommand<ENTITY, ID> implements
		ISearchCommand<ENTITY, ID>
{
	protected AbstractDatabaseSearchCommand(
			Class<? extends ENTITY> typeParameterClass,IDBCommandContext context)
	{
		super(typeParameterClass);
		this.setContext(context);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ENTITY> search(IQuery<ENTITY> query) {
		
		CriteriaQuery<ENTITY> criteriaQuery;

		if (query == null) {
			CriteriaBuilder criteriaBuilder = getEntityManager()
					.getCriteriaBuilder();

			criteriaQuery = (CriteriaQuery<ENTITY>) criteriaBuilder
					.createQuery(getEntityType());

			criteriaQuery.from(getEntityType());
		}

		criteriaQuery = (CriteriaQuery<ENTITY>) createCriteriaQuery(query);
		
		return getEntityManager().createQuery(criteriaQuery).getResultList();
	}

	/**
	 * @param query
	 * @return
	 */
	protected abstract CriteriaQuery<? extends ENTITY> createCriteriaQuery(IQuery<ENTITY> query);

}
