package org.divy.common.bo.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.persistence.criteria.*;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.command.ISearchCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;
import org.divy.common.bo.query.IQuery;


public abstract class AbstractDatabaseSearchCommand<ENTITY extends IBusinessObject<ID>, ID>
        extends
        AbstractDatabaseCommand<ENTITY, ID> implements
        ISearchCommand<ENTITY, ID> {

    protected AbstractDatabaseSearchCommand(
            Class<? extends ENTITY> typeParameterClass,EntityManagerCommandContext context)
    {
        super(typeParameterClass);
        this.setContext(context);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ENTITY> search(IQuery query) {

        CriteriaQuery<ENTITY> criteriaQuery;

        JPACriteriaQueryBuilder criteriaQueryBuilder = new JPACriteriaQueryBuilder(getEntityManager(), getEntityType());

        criteriaQuery = (CriteriaQuery<ENTITY>) criteriaQueryBuilder.createCriteriaQuery(query);

        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }
}
