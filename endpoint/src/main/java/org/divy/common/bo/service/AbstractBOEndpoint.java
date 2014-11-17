/**
 * 
 */
package org.divy.common.bo.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.divy.common.bo.IBORepository;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.business.defaults.DefaultBOManager;
import org.divy.common.bo.command.db.defaults.DefaultDatabaseRepository;
import org.divy.common.bo.query.IQuery;

/**
 * 
 * @author Divyakumar
 *
 */
public abstract class AbstractBOEndpoint<ENTITY extends IBusinessObject<ID>, ID extends Serializable> extends AbstractCRUDEndpoint<ENTITY, ID> {

    private IBOManager<ENTITY, ID> manager;

    public IBOManager<ENTITY, ID> getManager() {
        return manager;
    }

    public void setManager(IBOManager<ENTITY, ID> manager) {
        this.manager = manager;
    }

    public AbstractBOEndpoint(IBOManager<ENTITY, ID> manager) {
        this.manager = manager;
    }

    public AbstractBOEndpoint(IBORepository repository) {
        this(new DefaultBOManager<>( repository));
    }

    public AbstractBOEndpoint(String persistentUnitName,Class<ENTITY> entityClass) {
        this(new DefaultDatabaseRepository(persistentUnitName, entityClass));
    }

    protected List<ENTITY> doSearch(IQuery query){
        return manager.search(query);
    }

    protected ENTITY doCreate(ENTITY businessObject) {
        return manager.create(businessObject);
    }

    protected ENTITY doUpdate(ENTITY businessObject) {

        ENTITY updatedBo = manager.update(businessObject);

        return updatedBo;
    }

    protected void doDelete(ENTITY businessObject) {

        manager.delete(businessObject);

    }

    protected ENTITY doDelete(ID id) {

        return manager.deleteById(id);

    }

    protected List<ENTITY> doList() {

        List<ENTITY> boList = manager.list();

        List<ENTITY> resultList = new ArrayList<ENTITY>();

        resultList.addAll(boList);

        return resultList;

    }

    protected String getIdentity(ENTITY createdBo) {
        return createdBo.identity().toString();
    }

    protected ENTITY doGet(ID id) {
        return manager.get(id);


    }
}
