package org.divy.common.bo.repository.test;

import org.divy.common.bo.IBORepository;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.test.ITestDataProvider;
import org.divy.common.bo.test.TestBOCRUDBase;
import org.junit.Before;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public abstract class TestBaseDBRepository<E extends IBusinessObject<I>, I> extends TestBOCRUDBase<E, I>
{

    private IBORepository<E, I> boRepository;

    public TestBaseDBRepository(ITestDataProvider<E> testDataProvider) {
        super(testDataProvider);
    }

    @Before
    public void before() {

        boRepository = createRepository();

    }

    @Override
    protected E doCreateEntity(E entity) {
        return boRepository.create(entity);
    }

    @Override
    protected E doAssertExists(I id) {
        E entity = doGetByKey(id);
        assertThat(entity, notNullValue());
        return entity;
    }
    
    @Override
    protected E doGetByKey(I id) {
        return boRepository.get(id);
    }

    @Override
    protected void doAssertNotExists(I id) {
        E entity = doGetByKey(id);
        assertThat(entity, nullValue());
    }

    @Override
    protected void doUpdateEntity(I id, E entity) {
        boRepository.update(id, entity);
    }

    @Override
    protected void doDeleteEntity(E entity1) {
        boRepository.delete(entity1);
    }

    @Override
    protected List<E> doSearchEntities(Query searchQuery) {
        return boRepository.search(searchQuery);
    }

    protected abstract String getPersistentUnitName();

    protected abstract IBORepository<E, I> createRepository();
}
