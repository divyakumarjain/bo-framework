package org.divy.common.bo.rest;

import org.divy.common.bo.endpoint.hatoas.AbstractRepresentation;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class MockRepresentation extends AbstractRepresentation<UUID, Map<String, Object>, Link>
{
    public MockRepresentation() {}

    @Override public void addLink( Link link )
    {
    }

    @Override public Set<Link> getLinks()
    {
        return null;
    }
}

class Link {

}
