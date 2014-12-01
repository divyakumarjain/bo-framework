package org.divy.common.bo.repository.test;



import java.util.List;

import org.divy.common.bo.IBORepository;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.command.db.IDBCommandContext;
import org.divy.common.bo.context.DatabaseContext;
import org.divy.common.bo.query.IQuery;
import org.divy.common.bo.test.ITestDataProvider;
import org.divy.common.bo.test.TestBOCRUDBase;
import org.junit.Before;


public abstract class TestBaseDBRepository<ENTITY extends IBusinessObject<ID>, ID> extends TestBOCRUDBase<ENTITY, ID>
{

    /**
     * @param testDataProvider
     */
    public TestBaseDBRepository(ITestDataProvider<ENTITY, ID> testDataProvider) {
        super(testDataProvider);
    }

    protected IBORepository<ENTITY,ID> boRepository;
    protected IDBCommandContext context;

    @Before
    public void before() {
        context = new DatabaseContext(getpersistentUnitName());

        boRepository = createRepository();

    }

    @Override
    protected ENTITY doCreateEntity(ENTITY entity) {

        ENTITY createdEntity = boRepository.create(entity);

        return createdEntity;
    }

    @Override
    protected ENTITY doGetByKey(ID id) {
        return boRepository.get(id);
    }

    @Override
    protected void doUpdateEntity(ENTITY entity) {
        boRepository.update(entity);
    }

    @Override
    protected void doDeleteEntity(ENTITY entity1) {
        boRepository.delete(entity1);
    }

    @Override
    protected List<ENTITY> doSearchEntities(IQuery searchQuery) {
        return boRepository.search(searchQuery);
    }

    protected abstract String getpersistentUnitName();

    protected abstract IBORepository<ENTITY, ID> createRepository();
}
