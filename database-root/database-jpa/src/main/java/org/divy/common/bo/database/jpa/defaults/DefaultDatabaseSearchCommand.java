package org.divy.common.bo.database.jpa.defaults;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.command.AbstractDatabaseSearchCommand;
import org.divy.common.bo.database.jpa.EntityManagerCommandContext;
import org.divy.common.bo.database.jpa.JPACriteriaQueryBuilder;
import org.divy.common.bo.query.AttributeQuery;
import org.divy.common.bo.query.Query;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 *
 *
 */
public class DefaultDatabaseSearchCommand<E extends IBusinessObject<I>, I>
        extends AbstractDatabaseSearchCommand<E, I> {

    private final EntityManagerCommandContext context;

    public DefaultDatabaseSearchCommand(Class<E> entityClass,
            EntityManagerCommandContext context) {
        super(entityClass, context);
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> search(Query query) {

        CriteriaQuery<E> criteriaQuery;

        JPACriteriaQueryBuilder<E> criteriaQueryBuilder = new JPACriteriaQueryBuilder(context.getEntityManager(), getEntityType());

        if(query instanceof AttributeQuery || query == null) {
            criteriaQuery = criteriaQueryBuilder.createCriteriaQuery((AttributeQuery)query);
            return context.getEntityManager().createQuery(criteriaQuery).getResultList();
        } else {
            throw new IllegalArgumentException("Only Attribute Queries are supported");
        }
    }
}
