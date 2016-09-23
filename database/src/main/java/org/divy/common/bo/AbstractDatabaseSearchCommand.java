package org.divy.common.bo;

import org.divy.common.bo.command.ISearchCommand;
import org.divy.common.bo.query.EqualTo;
import org.divy.common.bo.query.Operator;
import org.divy.common.bo.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;


public abstract class AbstractDatabaseSearchCommand<E extends IBusinessObject<I>, I>
        extends
        AbstractDatabaseCommand<E, I> implements
        ISearchCommand<E, I>
{
    protected AbstractDatabaseSearchCommand(
            Class<E> typeParameterClass, IDBCommandContext context)
    {
        super(typeParameterClass);
        this.setContext(context);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> search(Query query) {

        CriteriaQuery<E> criteriaQuery;

        if (query == null) {
            CriteriaBuilder criteriaBuilder = getEntityManager()
                    .getCriteriaBuilder();

            criteriaQuery = (CriteriaQuery<E>) criteriaBuilder
                    .createQuery(getEntityType());

            criteriaQuery.from(getEntityType());
        }

        criteriaQuery = createCriteriaQuery(query);

        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    protected CriteriaQuery<E> createCriteriaQuery(
            Query query) {

        CriteriaQuery<E> criteriaQuery = null;

        CriteriaBuilder criteriaBuilder = getEntityManager()
                .getCriteriaBuilder();

        criteriaQuery = criteriaBuilder.createQuery(getEntityType());

        Root<? extends E> entityRoot = criteriaQuery.from(getEntityType());

        if(query!=null && !query.isEmpty()){
            List<Predicate> predicates = new ArrayList<>();

            for (Entry<String, Operator> entry : query.entrySet()) {
            	predicates.add(createPredicate(entry,criteriaBuilder,entityRoot));
            }
            criteriaQuery.where((Predicate[]) predicates.toArray(new Predicate[predicates.size()]));
        }

        return criteriaQuery;
    }

    @SuppressWarnings("unchecked")
    private Predicate createPredicate(Entry<String, Operator> iOperatorEntry,
                                      CriteriaBuilder cb, Root<? extends E> root) {

        Predicate returnPredicate = null;

        if (iOperatorEntry.getValue() instanceof EqualTo<?>) {
            EqualTo<String> equalToOperator = (EqualTo<String>) iOperatorEntry.getValue();
            returnPredicate = cb.equal(root.get(iOperatorEntry.getKey()),equalToOperator.getValue());
        }

        return returnPredicate;
    }

}
