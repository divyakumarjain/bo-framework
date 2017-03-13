package org.divy.common.bo.database.jpa.mock;

import org.divy.common.bo.AbstractBODatabaseRepository;
import org.divy.common.bo.database.jpa.context.DatabaseContext;
import org.divy.common.bo.database.jpa.defaults.DefaultDBCommandProvider;
import org.divy.common.bo.mapper.IBOMapper;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MockBODBRepository extends
        AbstractBODatabaseRepository<MockEntity, UUID> {

    public MockBODBRepository() {
        super(new DefaultDBCommandProvider<>(new DatabaseContext("org.divy.mock"), MockEntity.class, new IBOMapper<MockEntity, MockEntity>() {

            @Override
            public MockEntity createBO(MockEntity sourceData) {
                if (sourceData == null) {
                    return null;
                }
                MockEntity target = new MockEntity();
                this.mapFromBO(sourceData, target);
                return target;
            }

            @Override
            public MockEntity mapToBO(MockEntity sourceData, MockEntity businessObject) {
                businessObject.setName(sourceData.getName());
                businessObject.setIntegerAttribute(sourceData.getIntegerAttribute());
                businessObject.setParentEntity(this.createBO(sourceData.getParentEntity()));
                businessObject.setChildEntities((List<MockEntity>) createBO(businessObject.getChildEntities()));
                return businessObject;
            }

            @Override
            public MockEntity createFromBO(MockEntity businessObject) {
                return this.createBO(businessObject);
            }

            @Override
            public MockEntity mapFromBO(MockEntity businessObject, MockEntity targetData) {
                return this.mapToBO(businessObject, targetData);
            }

            @Override
            public Collection<MockEntity> createBO(Collection<MockEntity> sourceData) {
                return sourceData
                        .stream()
                        .map(this::createBO)
                        .collect(Collectors.toList());
            }

            @Override
            public Collection<MockEntity> createFromBO(Collection<MockEntity> businessObject) {
                return createBO(businessObject);
            }
        }));
    }

}
