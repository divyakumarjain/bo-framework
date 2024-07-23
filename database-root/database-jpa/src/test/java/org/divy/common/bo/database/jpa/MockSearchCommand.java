package org.divy.common.bo.database.jpa;

import org.divy.common.bo.database.command.impl.AbstractDatabaseSearchCommand;
import org.divy.common.bo.query.Query;

import jakarta.persistence.criteria.CriteriaQuery;
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
