package org.divy.common.bo.repository.test;

import org.divy.common.bo.IBORepository;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.IDBCommandContext;
import org.divy.common.bo.context.DatabaseContext;
import org.divy.common.bo.query.IQuery;
import org.divy.common.bo.test.ITestDataProvider;
import org.divy.common.bo.test.TestBOCRUDBase;
import org.junit.Before;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public abstract class TestBaseDBRepository<E extends IBusinessObject<I>, I> extends TestBOCRUDBase<E, I>
{

    protected IBORepository<E, I> boRepository;
    protected IDBCommandContext context;
    /**
     * @param testDataProvider
     */
    public TestBaseDBRepository(ITestDataProvider<E, I> testDataProvider) {
        super(testDataProvider);
    }

    @Before
    public void before() {
        context = new DatabaseContext(getpersistentUnitName());

        boRepository = createRepository();

    }

    @Override
    protected E doCreateEntity(E entity) {
        E createdEntity = boRepository.create(entity);
        return createdEntity;
    }

    @Override
    protected E doAssertExists(I id) {
        E entity = doGetByKey(id);
        assertThat(entity, notNullValue());
        return entity;
    }
    
	@Override
    protected E doGetByKey(I id) {
        E entity = boRepository.get(id);
        return entity;
	}

	@Override
    protected void doAssertNotExists(I id) {
        E entity = doGetByKey(id);
        assertThat(entity, nullValue());
	}

    @Override
    protected void doUpdateEntity(E entity) {
        boRepository.update(entity);
    }

    @Override
    protected void doDeleteEntity(E entity1) {
        boRepository.delete(entity1);
    }

    @Override
    protected List<E> doSearchEntities(IQuery searchQuery) {
        return boRepository.search(searchQuery);
    }

    protected abstract String getpersistentUnitName();

    protected abstract IBORepository<E, I> createRepository();
}
