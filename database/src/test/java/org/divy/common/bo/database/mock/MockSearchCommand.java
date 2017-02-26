package org.divy.common.bo.database.mock;

import org.divy.common.bo.database.AbstractDatabaseSearchCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;
import org.divy.common.bo.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.UUID;

/**
 *.a.jain@hp.com
 *
 */
public class MockSearchCommand extends
        AbstractDatabaseSearchCommand<MockEntity, UUID> {

    /**
     * @param context
     */
    public MockSearchCommand(EntityManagerCommandContext context) {
        super(MockEntity.class, context);
    }

    @SuppressWarnings("unchecked")
    protected CriteriaQuery<MockEntity> createCriteriaQuery(
            Query query) {
        CriteriaQuery<MockEntity> taskQuery;

        CriteriaBuilder criteriaBuilder = getEntityManager()
                .getCriteriaBuilder();

        taskQuery = criteriaBuilder
                .createQuery(getEntityType());

        taskQuery.from(getEntityType());

        return taskQuery;
    }

}
