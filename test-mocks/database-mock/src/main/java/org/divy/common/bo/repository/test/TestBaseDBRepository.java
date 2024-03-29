package org.divy.common.bo.repository.test;

import org.divy.common.bo.repository.BORepository;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.test.TestBOCRUDBase;
import org.divy.common.bo.test.TestDataProvider;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public abstract class TestBaseDBRepository<E extends BusinessObject<I>, I> extends TestBOCRUDBase<E, I>
{

    private BORepository<E, I> boRepository;

    protected TestBaseDBRepository(TestDataProvider<E> testDataProvider) {
        super(testDataProvider);
    }

    @BeforeEach
    public void before() {
        boRepository = createRepository();
    }

    @Override
    protected E doCreateEntity(E entity) {
        return boRepository.create(entity);
    }

    @Override
    protected E doAssertExists(I id) {
        var entity = doGetByKey(id);
        assertThat(entity, notNullValue());
        assertThat( entity.isPresent(),  equalTo( true ));
        return entity.get();
    }
    
    @Override
    protected Optional<E> doGetByKey(I id) {
        return boRepository.get(id);
    }

    @Override
    protected void doAssertNotExists(I id) {
        var entity = doGetByKey(id);
        assertThat(entity.isPresent(), equalTo( false ));
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
