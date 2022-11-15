package org.divy.common.bo.spring.mvc.graphql.hatoas;

import org.divy.common.bo.endpoint.hatoas.AbstractRepresentation;
import org.divy.common.bo.endpoint.hatoas.Representation;
import org.springframework.hateoas.Link;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SpringMVCRepresentation<I> extends AbstractRepresentation<I, Map<String, Object>, Link> implements Representation<I, Map<String, Object>, Link>{

    private Set<Link> links;

    public SpringMVCRepresentation() {
        data = new HashMap<>();
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
