package org.divy.common.bo.database.jpa.mock;

import org.divy.common.bo.database.command.impl.AbstractDatabaseSearchCommand;
import org.divy.common.bo.database.jpa.EntityManagerCommandContext;
import org.divy.common.bo.query.Query;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.UUID;

public class MockSearchCommand extends
        AbstractDatabaseSearchCommand<MockEntity, UUID>
{

    /**
     * @param context
     */
    public MockSearchCommand(EntityManagerCommandContext context) {
        super(MockEntity.class, context);
    }

    @SuppressWarnings("unchecked")
    protected CriteriaQuery<MockEntity> createCriteriaQuery(
            Query query) {
        return null;
    }

    @Override
    public List<MockEntity> search(Query query) {
        return null;
    }
}
