package org.divy.common.bo.command.db;

import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.command.ISearchCommand;
import org.divy.common.bo.query.IEqualTo;
import org.divy.common.bo.query.IOperator;
import org.divy.common.bo.query.IQuery;


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
	public List<ENTITY> search(IQuery query) {
		
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

	protected CriteriaQuery<? extends ENTITY> createCriteriaQuery(
			IQuery query) {

		CriteriaQuery<? extends ENTITY> criteriaQuery = null;

		CriteriaBuilder criteriaBuilder = getEntityManager()
				.getCriteriaBuilder();

		criteriaQuery = criteriaBuilder.createQuery(getEntityType());

		Root<? extends ENTITY> entityRoot = criteriaQuery.from(getEntityType());
		
		EntityType<? extends ENTITY> entityMetadata = entityRoot.getModel();
		
		if(query!=null){
			Collection<Entry<String, IOperator>> queryOperatorCollection = query.getAll();
			
			Entry<String, IOperator>[] queryOperators = new Entry[queryOperatorCollection.size()];
			
			queryOperators = queryOperatorCollection.toArray(queryOperators);
			
			Predicate[] predicates = new Predicate[queryOperatorCollection.size()];
			
			for (int i = 0; i < queryOperators.length; i++) {
				predicates[i] = createPredicate(queryOperators[i],criteriaBuilder,entityRoot);
			}		
			criteriaQuery.where(predicates);
		}

		return criteriaQuery;
	}

	private Predicate createPredicate(Entry<String, IOperator> iOperatorEntry,
			CriteriaBuilder cb,Root<? extends ENTITY> root ) {
		
		Predicate returnPredicate = null;
		
		if (iOperatorEntry.getValue() instanceof IEqualTo<?>) {
			IEqualTo<String> equalToOperator = (IEqualTo<String>) iOperatorEntry.getValue();
			returnPredicate = cb.equal(root.get(iOperatorEntry.getKey()),equalToOperator.getValue());
		}
		
		return returnPredicate;
	}

}
