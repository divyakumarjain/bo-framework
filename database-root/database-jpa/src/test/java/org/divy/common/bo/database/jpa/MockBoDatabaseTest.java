package org.divy.common.bo.database.jpa;

import org.divy.common.bo.repository.BORepository;
import org.divy.common.bo.repository.test.TestBaseDBRepository;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class MockBoDatabaseTest extends TestBaseDBRepository<MockEntity,UUID> {

    public MockBoDatabaseTest() {
        super(new MockBoTestDataProvider());
    }

    @Override
    protected String getPersistentUnitName() {
        return "org.divy.mock";
    }

    @Override
    protected BORepository<MockEntity, UUID> createRepository() {
        return new MockBODBRepository();
    }

    @Override
    protected UUID getIdentifier(MockEntity entity) {
        return entity.identity();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.divy.common.bo.test.TestBaseManager#extendedTestCreatedEntity(org
     * .divy.common.bo.BusinessObject)
     */
    @Override
    protected void extendedTestCreatedEntity(MockEntity entity) {

        assertThat("Child Mock objects list should not be null",entity.getChildEntities(), not(nullValue()));
        assertThat("Child Mock object list should be one",entity.getChildEntities().size(), equalTo(1));
        assertThat("Child Mock object should not be null",entity.getChildEntities().get(0), not(nullValue()));

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.divy.common.bo.test.TestBaseManager#extendedTestUpdatedEntity(org
     * .divy.common.bo.BusinessObject)
     */
    @Override
    protected void extendedTestUpdatedEntity(MockEntity entity) {
        assertThat("Child Mock objects list should not be null",entity.getChildEntities(), not(nullValue()));
        assertThat("Child Mock object list should be one",entity.getChildEntities().size(), equalTo(2));
        assertThat("Child Mock object should not be null",entity.getChildEntities().get(0), not(nullValue()));
    }
}
