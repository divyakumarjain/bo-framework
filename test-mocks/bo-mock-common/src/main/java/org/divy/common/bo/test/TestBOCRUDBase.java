/**
 * 
 */
package org.divy.common.bo.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.Iterator;
import java.util.List;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.query.IQuery;
import org.divy.common.bo.query.defaults.Query;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Divyakumar
 *
 * @param <ENTITY>
 * @param <ID>
 */
public abstract class TestBOCRUDBase<ENTITY extends IBusinessObject<ID>, ID> {

    final protected ITestDataProvider<ENTITY, ID> testDataProvider;

    /**
     *
     */
    public TestBOCRUDBase(ITestDataProvider<ENTITY, ID> testDataProvider) {
        super();
        this.testDataProvider = testDataProvider;
    }

    @Test
    public void testCreate() {
        ENTITY entity = testDataProvider.getEntityInstance();

        testDataProvider.fillTestDataSet1(entity);

        doCreateEntity(entity);

        doAssertExists(getIdentifier(entity));

        extendedTestCreatedEntity(entity);

    }

    abstract protected ID getIdentifier(ENTITY entity);

    @Test
    public void testUpdate() {
        ENTITY entity = testDataProvider.getEntityInstance();

        testDataProvider.fillTestDataSet2(entity);

        doCreateEntity(entity);

        entity = doAssertExists(getIdentifier(entity));

        testDataProvider.modifyEntityWithTestData(entity);

        doUpdateEntity(entity);

        ID id = getIdentifier(entity);

        ENTITY updatedEntity = doAssertExists(id);

        assertThat("Updated Entity should be same",updatedEntity,equalTo(entity));

        extendedTestUpdatedEntity(entity);
    }

    @Test
    public void testDelete() {
        ENTITY entity1 = testDataProvider.getEntityInstance();
        ENTITY entity2 = testDataProvider.getEntityInstance();

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
        ENTITY entity1 = testDataProvider.getEntityInstance();

        testDataProvider.fillTestDataSet1(entity1);

        doCreateEntity(entity1);

        ENTITY entity2 = testDataProvider.getEntityInstance();

        testDataProvider.fillTestDataSet2(entity2);

        doCreateEntity(entity2);

        IQuery searchQuery = testDataProvider.createSearchQuery();

        List<ENTITY> searchedEntities = doSearchEntities(searchQuery);

        assertThat("Search result should not be null", searchedEntities,
                notNullValue());

        assertFalse("Search result should not be empty",
                searchedEntities.isEmpty());

        assertThat("Search result should be single", searchedEntities.size(),
                equalTo(1));

    }

    /* Clean up */
    @After
    public void cleanup() throws Exception{
        List<ENTITY> searchedEntities = doSearchEntities(new Query());

        for (Iterator<ENTITY> iterator = searchedEntities.iterator(); iterator.hasNext();) {

            try {

                ENTITY entity = iterator.next();
                doDeleteEntity(entity);

            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }


    /* CRUD Operation */
    protected abstract ENTITY doCreateEntity(ENTITY entity);
    protected abstract void doUpdateEntity(ENTITY entity);
    protected abstract ENTITY doGetByKey(ID id);
    protected abstract ENTITY doAssertExists(ID id);
    protected abstract void doAssertNotExists(ID id);
    protected abstract void doDeleteEntity(ENTITY entity);
    protected abstract List<ENTITY> doSearchEntities(IQuery searchQuery);

    /* Extended Tests */
    protected abstract void extendedTestCreatedEntity(ENTITY entity);
    protected abstract void extendedTestUpdatedEntity(ENTITY entity);

}