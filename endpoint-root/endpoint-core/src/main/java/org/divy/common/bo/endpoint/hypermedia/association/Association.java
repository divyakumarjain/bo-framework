package org.divy.common.bo.endpoint.hypermedia.association;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.rest.AbstractHyperMediaMapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Association<T extends BusinessObject<I>, I> {
    protected AbstractHyperMediaMapper hyperMediaMapper;
    private String name;
    private Cardinality cardinality;
    private List<PropagateSave> propagateSaves;
    private Reader reader;
    private boolean includeInReadOperation;
    private BOManager<T, I> manager;
    private Create create;
    private Update update;



    public Optional<Object> read(Object source, Object... argv) {

        return getReader().read(source, argv).map(value -> {
            if (hyperMediaMapper != null) {
                if (value instanceof Collection) {
                    return hyperMediaMapper.createRepresentationFromBO((Collection) value);
                } else if(value instanceof BusinessObject) {
                    return hyperMediaMapper.createRepresentationFromBO((BusinessObject)value);
                } else {
                    throw new IllegalArgumentException("Not supported Type");
                }
                
            } else {
                return value;
            }
        });
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cardinality getCardinality() {
        return cardinality;
    }

    public void setCardinality(Cardinality cardinality) {
        this.cardinality = cardinality;
    }

    public List<PropagateSave> getPropagateSaves() {
        return propagateSaves;
    }

    public void setPropagateSaves(List<PropagateSave> propagateSaves) {
        this.propagateSaves = propagateSaves;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    protected void setCreate(Create create) {
        this.create = create;
    }


    protected void setUpdater(Update update) {
        this.update = update;
    }

    public boolean isIncludeInReadOperation() {
        return includeInReadOperation;
    }

    public void setIncludeInReadOperation(boolean includeInReadOperation) {
        this.includeInReadOperation = includeInReadOperation;
    }

    public BOManager<T, I> getManager()
    {
        return manager;
    }

    public void setManager(BOManager<T, I> manager)
    {
        this.manager = manager;
    }
}
