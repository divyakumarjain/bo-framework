
/**
 * 
 */
package org.divy.common.bo.test;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.query.Query;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * @author Divyakumar
 *
 * @param <E>
 * @param <I>
 */
public abstract class TestBOCRUDBase<E extends IBusinessObject<I>, I> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestBOCRUDBase.class);

    protected final ITestDataProvider<E> testDataProvider;

    /**
     *
     */
    public TestBOCRUDBase(ITestDataProvider<E> testDataProvider) {
        super();
        this.testDataProvider = testDataProvider;
    }

    @Test
    public void testCreate() {
        E entity = testDataProvider.getEntityInstance();

        testDataProvider.fillTestDataSet1(entity);

        doCreateEntity(entity);

        doAssertExists(getIdentifier(entity));

        extendedTestCreatedEntity(entity);

    }

    protected abstract I getIdentifier(E entity);

    @Test
    public void testUpdate() {
        E entity = testDataProvider.getEntityInstance();

        testDataProvider.fillTestDataSet2(entity);

        doCreateEntity(entity);

        entity = doAssertExists(getIdentifier(entity));

        testDataProvider.modifyEntityWithTestData(entity);

        doUpdateEntity(entity);

        I id = getIdentifier(entity);

        E updatedEntity = doAssertExists(id);

        assertThat("Updated Entity should be same",updatedEntity,equalTo(entity));

        extendedTestUpdatedEntity(entity);
    }

    @Test
    public void testDelete() {
        E entity1 = testDataProvider.getEntityInstance();
        E entity2 = testDataProvider.getEntityInstance();

        testDataProvider.fillTestDataSet1(entity1);
        testDataProvider.fillTestDataSet2(entity2);

        entity1 = doCreateEntity(entity1);
        entity2 = doCreateEntity(entity2);

        doDeleteEntity(entity1);

        doAssertNotExists(getIdentifier(entity1));
        entity2 = doAssertExists(getIdentifier(entity2));

    }

    @Test
    @Ignore
    //TODO: Finish Search framework
    //FIXME:
    public void testSearch() {
        E entity1 = testDataProvider.getEntityInstance();

        testDataProvider.fillTestDataSet1(entity1);

        doCreateEntity(entity1);

        E entity2 = testDataProvider.getEntityInstance();

        testDataProvider.fillTestDataSet2(entity2);

        doCreateEntity(entity2);

        Query searchQuery = testDataProvider.createSearchQuery();

        List<E> searchedEntities = doSearchEntities(searchQuery);

        assertThat("Search result should not be null", searchedEntities,
                notNullValue());

        assertFalse("Search result should not be empty",
                searchedEntities.isEmpty());

        assertThat("Search result should be single", searchedEntities.size(),
                equalTo(1));

    }

    /* Clean up */
    @After
    public void cleanup() {
        List<E> searchedEntities = doSearchEntities(new org.divy.common.bo.query.defaults.Query());

        for (Iterator<E> iterator = searchedEntities.iterator(); iterator.hasNext(); ) {

            try {

                E entity = iterator.next();
                doDeleteEntity(entity);

            } catch (Exception e) {
                LOGGER.error("Could not clean up the test case", e);
            }
        }
    }


    /* CRUD Operation */
    protected abstract E doCreateEntity(E entity);

    protected abstract void doUpdateEntity(E entity);

    protected abstract E doGetByKey(I id);

    protected abstract E doAssertExists(I id);

    protected abstract void doAssertNotExists(I id);

    protected abstract void doDeleteEntity(E entity);

    protected abstract List<E> doSearchEntities(Query searchQuery);

    /* Extended Tests */
    protected abstract void extendedTestCreatedEntity(E entity);

    protected abstract void extendedTestUpdatedEntity(E entity);

}