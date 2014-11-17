package org.divy.common.bo.database.mock;

import org.divy.common.bo.IBORepository;
import org.divy.common.bo.repository.test.TestBaseDBRepository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class MockBoDatabaseTest extends TestBaseDBRepository<MockEntity,String> {

    public MockBoDatabaseTest() {
        super(new MockBoTestDataProvider());
    }

    @Override
    protected String getPersistantUnitName() {
        return "org.divy.task";
    }

    @Override
    protected IBORepository<MockEntity, String> createRepository() {
        return new MockBODBRepository();
    }

    @Override
    protected String getIdentifier(MockEntity entity) {
        return entity.identity();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.divy.common.bo.test.TestBaseManager#extendedTestCreatedEntity(org
     * .divy.common.bo.IBusinessObject)
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
     * .divy.common.bo.IBusinessObject)
     */
    @Override
    protected void extendedTestUpdatedEntity(MockEntity entity) {
        assertThat("Child Mock objects list should not be null",entity.getChildEntities(), not(nullValue()));
        assertThat("Child Mock object list should be one",entity.getChildEntities().size(), equalTo(2));
        assertThat("Child Mock object should not be null",entity.getChildEntities().get(0), not(nullValue()));
    }
}
