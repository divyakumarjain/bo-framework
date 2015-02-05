package org.divy.common.bo.database.mock;

import org.divy.common.bo.AbstractDatabaseUpdateCommand;
import org.divy.common.bo.IDBCommandContext;
import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.defaults.AdvanceBOMapper;
import org.dozer.classmap.RelationshipType;
import org.dozer.loader.api.FieldsMappingOptions;
import org.dozer.loader.api.TypeMappingBuilder;

public class MockUpdateCommand extends AbstractDatabaseUpdateCommand<MockEntity> {

    public MockUpdateCommand(
            IDBCommandContext context) {
        super(MockEntity.class, context);
        mapper = new AdvanceBOMapper<MockEntity,MockEntity>(MockEntity.class,MockEntity.class){

            @Override
            protected void configureMapping(TypeMappingBuilder mapping) {
                super.configureMapping(mapping);

                mapping.fields("childEntities","childEntities"
                        ,FieldsMappingOptions.oneWay()
                        ,FieldsMappingOptions.copyByReference()
                        ,FieldsMappingOptions.removeOrphans()
                        ,FieldsMappingOptions.collectionStrategy(true, RelationshipType.CUMULATIVE));

            }

        };
    }


    IBOMapper<MockEntity,MockEntity> mapper;


    @Override
    protected void merge(MockEntity source, MockEntity target) {
        if(source != target)
            mapper.mapToBO(source, target);
    }
}
