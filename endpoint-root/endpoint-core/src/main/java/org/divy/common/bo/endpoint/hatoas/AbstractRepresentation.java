package org.divy.common.bo.endpoint.hatoas;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRepresentation<I, D, L> implements Representation<I, D, L> {

    private I id;
    private String type;
    protected D data;

    private Map<String, Object> associations = new HashMap<>();

    protected AbstractRepresentation(D data)
    {
        this.data = data;
    }

    protected AbstractRepresentation()
    {
    }

    @Override
    public D getData() {
        return data;
    }

    @Override
    public void setData(D data) {
        this.data = data;
    }

    @Override
    @JsonProperty
    public I getId() {
        return id;
    }

    @Override
    public void setId(I id) {
        this.id = id;
    }

    @Override
    public String _type() {
        return type;
    }

    @Override
    public void _type(String type) {
        this.type = type;
    }

    @Override
    public I identity() {
        return id;
    }

    @Override
    public Map<String, Object> getAssociations() {
        return associations;
    }

    public void setAssociations(Map<String, Object> associations) {
        this.associations = associations;
    }

    @Override
    public void addAssociation(String rel, Object association) {
        getAssociations().put(rel, association);
    }
}
