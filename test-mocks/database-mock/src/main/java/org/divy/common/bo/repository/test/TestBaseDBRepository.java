package org.divy.common.bo.repository.test;

import org.divy.common.bo.BORepository;
import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.test.TestBOCRUDBase;
import org.divy.common.bo.test.TestDataProvider;
import org.junit.Before;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public abstract class TestBaseDBRepository<E extends BusinessObject<I>, I> extends TestBOCRUDBase<E, I>
{

    private BORepository<E, I> boRepository;

    public TestBaseDBRepository(TestDataProvider<E> testDataProvider) {
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

    protected abstract BORepository<E, I> createRepository();
}
