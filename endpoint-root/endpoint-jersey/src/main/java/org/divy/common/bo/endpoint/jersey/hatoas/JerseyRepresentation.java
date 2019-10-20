package org.divy.common.bo.endpoint.jersey.hatoas;

import org.divy.common.bo.endpoint.hatoas.AbstractRepresentation;

import javax.ws.rs.core.Link;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JerseyRepresentation<I> extends AbstractRepresentation<I, Map<String, Object>, Link>
{

    private Set<Link> links;


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
}
