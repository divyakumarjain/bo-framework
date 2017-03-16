package org.divy.common.bo.endpoint.hypermedia;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.core.Link;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractRepresentation<I> {

    private I id;

    private Set<Link> links;
    private Map<String, Object> associations = new HashMap<>();
    private Map<String, Object> data;

    public AbstractRepresentation() {
        data = new HashMap<>();
    }

    public I identity() {
        return id;
    }

    @JsonProperty
    public I getId() {
        return id;
    }

    public void setId(I id) {
        this.id = id;
    }

    public void addLink(Link link) {
        if(links==null) {
            links = new HashSet<>();
        }

        links.add(link);
    }

    public Set<Link> getLinks() {
        return links;
    }

    public void setLinks(Set<Link> links) {
        this.links = links;
    }


    public Map<String, Object> getAssociations() {
        return associations;
    }

    public void setAssociations(Map<String, Object> associations) {
        this.associations = associations;
    }

    public void addAssociation(String rel, Object association) {
        getAssociations().put(rel, association);
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
