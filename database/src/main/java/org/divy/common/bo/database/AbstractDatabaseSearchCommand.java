package org.divy.common.bo.database;

import java.util.List;

import javax.persistence.criteria.*;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.command.ISearchCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;
import org.divy.common.bo.query.AttributeQuery;
import org.divy.common.bo.query.Query;


public abstract class AbstractDatabaseSearchCommand<E extends IBusinessObject<I>, I>
        extends AbstractDatabaseCommand<E, I>
        implements ISearchCommand<E, I> {

    protected AbstractDatabaseSearchCommand(
            Class<E> typeParameterClass, EntityManagerCommandContext context)
    {
        super(typeParameterClass);
        this.setContext(context);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> search(Query query) {

        CriteriaQuery<E> criteriaQuery;

        JPACriteriaQueryBuilder criteriaQueryBuilder = new JPACriteriaQueryBuilder(getEntityManager(), getEntityType());

        if(query instanceof AttributeQuery || query == null) {
            criteriaQuery = (CriteriaQuery<E>) criteriaQueryBuilder.createCriteriaQuery((AttributeQuery)query);
            return getEntityManager().createQuery(criteriaQuery).getResultList();
        } else {
            throw new IllegalArgumentException("Only Attribute Queries are supported");
        }
    }
}
