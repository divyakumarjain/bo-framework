package org.divy.common.bo.endpoint.test;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.json.test.KeyGenerator;
import org.divy.common.bo.query.IQuery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryBOManager<E extends IBusinessObject<I>, I extends Serializable> implements IBOManager<E, I> {

    private KeyGenerator<E, I> keyGenerator;
    private Map<I, E> inMemoryMap = new HashMap<>();

    public InMemoryBOManager(KeyGenerator<E, I> keyGenerator) {
        this.keyGenerator = keyGenerator;
    }
    
    public InMemoryBOManager() {
    }

    @Override
    public E create(E businessObject) {
        if(keyGenerator !=null) {
            keyGenerator.initializeKey(businessObject);
        }
        inMemoryMap.put(businessObject.identity(),businessObject);
        return businessObject;
    }

    @Override
    public E update(E businessObject) {
        inMemoryMap.put(businessObject.identity(),businessObject);
        return businessObject;
    }

    @Override
    public E delete(E businessObject) {
        return inMemoryMap.remove(businessObject.identity());
    }

    @Override
    public List<E> list() {
        ArrayList<E> arrayList = new ArrayList<>();
        arrayList.addAll(inMemoryMap.values());
        return arrayList;
    }

    @Override
    public List<E> search(IQuery businessObjectQuery) {
        ArrayList<E> arrayList = new ArrayList<>();
        arrayList.addAll(inMemoryMap.values());
        return arrayList;
    }

    @Override
    public E deleteById(I id) {
        return inMemoryMap.remove(id);
    }

    @Override
    public E get(I identity) {
        return inMemoryMap.get(identity);
    }
}
