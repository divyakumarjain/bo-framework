package org.divy.common.bo.test;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.query.AttributeQuery;
import org.divy.common.bo.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.AfterEach;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.IsNull.*;


/**
 *
 *
 * @param <E>
 * @param <I>
 */
public abstract class TestBOCRUDBase<E extends BusinessObject<I>, I> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestBOCRUDBase.class);

    private final TestDataProvider<E> testDataProvider;

    /**
     *
     */
    public TestBOCRUDBase(TestDataProvider<E> testDataProvider) {
        super();
        this.testDataProvider = testDataProvider;
    }

    @Test
    public void testCreate() {
        var entity = testDataProvider.getEntityInstance();

        testDataProvider.fillTestDataSet1(entity);

        doCreateEntity(entity);

        doAssertExists(getIdentifier(entity));

        extendedTestCreatedEntity(entity);

    }

    protected abstract I getIdentifier(E entity);

    @Test
    public void testUpdate() {
        var entity = testDataProvider.getEntityInstance();

        testDataProvider.fillTestDataSet2(entity);

        doCreateEntity(entity);

        entity = doAssertExists(getIdentifier(entity));

        testDataProvider.modifyEntityWithTestData(entity);

        var id = getIdentifier(entity);

        doUpdateEntity(id, entity);

        var updatedEntity = doAssertExists(id);

        assertThat("Updated Entity should be same",updatedEntity,equalTo(entity));

        extendedTestUpdatedEntity(entity);
    }

    @Test
    public void testDelete() {
        var entity1 = testDataProvider.getEntityInstance();
        var entity2 = testDataProvider.getEntityInstance();

        testDataProvider.fillTestDataSet1(entity1);
        testDataProvider.fillTestDataSet2(entity2);

        entity1 = doCreateEntity(entity1);
        entity2 = doCreateEntity(entity2);

        doDeleteEntity(entity1);

        doAssertNotExists(getIdentifier(entity1));
        doAssertExists(getIdentifier(entity2));

    }
 
    @Test
    @Disabled
    //TODO: Finish Search framework
    public void testSearch() {
        var entity1 = testDataProvider.getEntityInstance();

        testDataProvider.fillTestDataSet1(entity1);

        doCreateEntity(entity1);

        var entity2 = testDataProvider.getEntityInstance();

        testDataProvider.fillTestDataSet2(entity2);

        doCreateEntity(entity2);

        var searchQuery = testDataProvider.createSearchQuery();

        List<E> searchedEntities = doSearchEntities(searchQuery);

        assertThat("Search result should not be null", searchedEntities,
              notNullValue());

        assertThat("Search result should be single", searchedEntities.size(),
              equalTo(1));

    }

    /* Clean up */
    @AfterEach
    public void cleanup() {
        try {
            List<E> searchedEntities = doSearchEntities(new AttributeQuery());
            searchedEntities.forEach(this::doDeleteEntity);
        } catch (Exception e) {
            LOGGER.error("Exception during cleanup", e);
        }
    }


    /* CRUD Operation */
    protected abstract E doCreateEntity(E entity);

    protected abstract void doUpdateEntity(I id, E entity);

    protected abstract Optional<E> doGetByKey(I id);

    protected abstract E doAssertExists(I id);

    protected abstract void doAssertNotExists(I id);

    protected abstract void doDeleteEntity(E entity);

    protected abstract List<E> doSearchEntities(Query searchQuery);

    /* Extended Tests */
    protected abstract void extendedTestCreatedEntity(E entity);

    protected abstract void extendedTestUpdatedEntity(E entity);

}
