package org.divy.common.bo.database.jpa;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.metadata.FieldMetaData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BoEntityMetaDataProviderTest {
    @Test
    public void getEntityTypes() {
        ArrayList<Class<? extends BusinessObject>> typeList = new ArrayList<>();
        typeList.add(MockEntity.class);
        BoEntityMetaDataProvider metaDataProvider = new BoEntityMetaDataProvider(typeList);
        assertThat(metaDataProvider.getEntityTypes(), is(equalTo(typeList)));
    }

    @Test
    public void getChildEntity() {
        ArrayList<Class<? extends BusinessObject>> typeList = new ArrayList<>();
        typeList.add(MockEntity.class);

        BoEntityMetaDataProvider metaDataProvider = new BoEntityMetaDataProvider(typeList);
        Map<String, FieldMetaData> childEntity = metaDataProvider.getChildEntities(MockEntity.class);

        assertThat(childEntity, hasEntry(equalTo("parentEntity"), equalTo(new FieldMetaData("parentEntity", MockEntity.class, false))));
        assertThat(childEntity, hasEntry(equalTo("childEntities"), equalTo(new FieldMetaData("childEntities", MockEntity.class, true))));
    }

}
