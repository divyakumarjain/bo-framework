package org.divy.common.bo.endpoint.association;

import org.divy.common.bo.AbstractBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.association.builder.CreateBuilder;
import org.divy.common.bo.endpoint.association.builder.UpdateBuilder;
import org.divy.common.bo.endpoint.association.reader.ReaderBuilder;
import org.divy.common.rest.impl.AbstractHATEOASMapper;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Association<T> {
    protected AbstractHATEOASMapper mapper;
    private String name;
    private Cardinality cardinality;
    private List<PropagateSave> propagateSaves;
    private Reader reader;
    private boolean includeInReadOperation;
    private IBOManager<?, ?> manager;
    private Create create;
    private Update update;

    public Association<T> withMapper(AbstractHATEOASMapper mapper) {
        this.mapper = mapper;
        return this;
    }

    public Association<T> manager(IBOManager<?, ?> manager) {
        this.manager = manager;
        return this;
    }

    public Optional<Object> read(Object source, Object... argv) {

        return getReader().read(source, argv).map(value -> {
            if (mapper != null) {
                if (value instanceof Collection) {
                    return mapper.createFromBO((Collection) value);
                } else if(value instanceof AbstractBusinessObject) {
                    return mapper.createFromBO((AbstractBusinessObject)value);
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

    private void setCreate(Create create) {
        this.create = create;
    }


    private void setUpdater(Update update) {
        this.update = update;
    }

    public boolean isIncludeInReadOperation() {
        return includeInReadOperation;
    }

    public void setIncludeInReadOperation(boolean includeInReadOperation) {
        this.includeInReadOperation = includeInReadOperation;
    }

    public Association<T> name(String name) {
        this.name = name;
        return this;
    }

    public Association<T> includeInRead() {
        setIncludeInReadOperation(true);
        return this;
    }

    public Association<T> propagateSave(PropagateSave ... propagateSaves) {
        setPropagateSaves(Arrays.asList(propagateSaves));
        return this;
    }

    public Association<T> cardinality(Cardinality cardinality) {
        this.cardinality = cardinality;
        ReaderBuilder readerBuilder = new ReaderBuilder();
        setReader(readerBuilder);
        return this;
    }

    public T readWith(Class<T> groupClass) {
        ReaderBuilder readerBuilder = new ReaderBuilder();
        setReader(readerBuilder);
        return readerBuilder.withMethodOn(groupClass);
    }

    public T createWith(Class<T> groupClass) {
        CreateBuilder createBuilder = new CreateBuilder();
        setCreate(createBuilder);
        return createBuilder.createWith(groupClass);
    }

    public T updateWith(Class<T> groupClass) {
        UpdateBuilder updateBuilder = new UpdateBuilder();
        setUpdater(updateBuilder);
        return updateBuilder.updateWith(groupClass);
    }


    public AttributeReader attribute(String attributeName) {
        ReaderBuilder readerBuilder = new ReaderBuilder();
        setReader(readerBuilder);
        return readerBuilder.attribute(attributeName);
    }
}
