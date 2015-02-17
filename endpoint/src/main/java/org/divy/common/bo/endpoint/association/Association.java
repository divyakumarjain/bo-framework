package org.divy.common.bo.endpoint.association;

import java.util.Arrays;
import java.util.List;

/**
 * Created by divyjain on 2/5/2015.
 */
public class Association<T> {
    private String name;
    private Cardinality cardinality;
    private List<PropagateSave> propagateSaves;
    private Reader reader;
    private boolean includeInReadOperation;

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

    public ReaderBuilder cardinality(Cardinality cardinality) {
        this.cardinality = cardinality;
        ReaderBuilder readerBuilder = new ReaderBuilder();
        setReader(readerBuilder);
        return  readerBuilder;
    }

}
