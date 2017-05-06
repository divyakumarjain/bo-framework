package org.divy.common.bo.endpoint.hypermedia;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractRepresentation<I, D, L> implements Representation<I, D, L> {

    private I id;
    private String type;
    D data;

    @Override
    public D getData() {
        return data;
    }

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
}
