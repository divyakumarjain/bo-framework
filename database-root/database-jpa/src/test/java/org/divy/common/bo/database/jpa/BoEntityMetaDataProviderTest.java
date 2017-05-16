package org.divy.common.bo.database.jpa;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.database.jpa.mock.MockEntity;
import org.divy.common.bo.metadata.FieldMetaData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class BoEntityMetaDataProviderTest {
    @Test
    public void getEntityTypes() throws Exception {
        ArrayList<Class<? extends BusinessObject>> typeList = new ArrayList<>();
        typeList.add(MockEntity.class);
        BoEntityMetaDataProvider metaDataProvider = new BoEntityMetaDataProvider(typeList);
        assertThat(metaDataProvider.getEntityTypes(), is(equalTo(typeList)));
    }

    @Test
    public void getChildEntity() throws Exception {
        ArrayList<Class<? extends BusinessObject>> typeList = new ArrayList<>();
        typeList.add(MockEntity.class);

        BoEntityMetaDataProvider metaDataProvider = new BoEntityMetaDataProvider(typeList);
        Map<String, FieldMetaData> childEntity = metaDataProvider.getChildEntity(MockEntity.class);

        assertThat(childEntity, hasEntry(equalTo("parentEntity"), equalTo(new FieldMetaData("parentEntity", MockEntity.class, false))));
        assertThat(childEntity, hasEntry(equalTo("childEntities"), equalTo(new FieldMetaData("childEntities", MockEntity.class, true))));
    }

}