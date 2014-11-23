package org.divy.common.bo.endpoint.test;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.json.test.KeyGenerator;
import org.divy.common.bo.query.IQuery;
import org.divy.common.bo.query.defaults.EqualTo;
import org.divy.common.bo.query.defaults.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryBOManager<ENTITY extends IBusinessObject<ID>, ID extends Serializable> implements IBOManager<ENTITY,ID> {

    private KeyGenerator<ENTITY,ID> keyGenerator;
    private Map<ID,ENTITY> inMemoryMap = new HashMap();

    public InMemoryBOManager(KeyGenerator<ENTITY,ID> keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    @Override
    public ENTITY create(ENTITY businessObject) {
        if(keyGenerator !=null) {
            keyGenerator.initializeKey(businessObject);
        }
        inMemoryMap.put(businessObject.identity(),businessObject);
        return businessObject;
    }

    @Override
    public ENTITY update(ENTITY businessObject) {
        inMemoryMap.put(businessObject.identity(),businessObject);
        return businessObject;
    }

    @Override
    public ENTITY delete(ENTITY businessObject) {
        return inMemoryMap.remove(businessObject.identity());
    }

    @Override
    public List<ENTITY> list() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(inMemoryMap.values());
        return arrayList;
    }

    @Override
    public List<ENTITY> search(IQuery businessObjectQuery) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(inMemoryMap.values());
        return arrayList;
    }

    @Override
    public ENTITY deleteById(ID id) {
        return inMemoryMap.remove(id);
    }

    @Override
    public ENTITY get(ID identity) {
        return inMemoryMap.get(identity);
    }
}
