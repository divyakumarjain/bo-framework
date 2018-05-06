package org.divy.common.bo.endpoint.jersey.hypermedia;

import org.divy.common.bo.endpoint.hypermedia.AbstractRepresentation;

import javax.ws.rs.core.Link;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JerseyRepresentation<I> extends AbstractRepresentation<I, Map<String, Object>, Link>
{

    private Set<Link> links;
    private Map<String, Object> associations = new HashMap<>();


    public JerseyRepresentation() {
        super(new HashMap<>());
    }

    @Override
    public void addLink(Link link) {
        if(links==null) {
            links = new HashSet<>();
        }

        links.add(link);
    }

    @Override
    public Set<Link> getLinks() {
        return links;
    }

    public void setLinks(Set<Link> links) {
        this.links = links;
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
