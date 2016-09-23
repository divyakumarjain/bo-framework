/**
 * 
 */
package org.divy.common.bo.database.mock;

import java.util.UUID;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.divy.common.bo.AbstractDatabaseSearchCommand;
import org.divy.common.bo.IDBCommandContext;
import org.divy.common.bo.query.Query;

/**
 * @author divyakumar.a.jain@hp.com
 *
 */
public class MockSearchCommand extends
        AbstractDatabaseSearchCommand<MockEntity, UUID> {

    /**
     * @param context
     */
    public MockSearchCommand(IDBCommandContext context) {
        super(MockEntity.class, context);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected CriteriaQuery<MockEntity> createCriteriaQuery(
            Query query) {
        CriteriaQuery<MockEntity> taskQuery = null;

        CriteriaBuilder criteriaBuilder = getEntityManager()
                .getCriteriaBuilder();

        taskQuery = (CriteriaQuery<MockEntity>) criteriaBuilder
                .createQuery(getEntityType());

        taskQuery.from(getEntityType());

        return taskQuery;
    }

}
