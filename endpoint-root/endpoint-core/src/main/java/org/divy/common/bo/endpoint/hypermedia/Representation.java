package org.divy.common.bo.endpoint.hypermedia;

import org.divy.common.bo.Identifiable;

import java.util.Map;
import java.util.Set;

public interface Representation<I, D, L> extends Identifiable<I> {

    I getId();

    void addLink(L link);

    Set<L> getLinks();

    Map<String, Object> getAssociations();

    void addAssociation(String rel, Object association);

    D getData();

    void setId(I identity);
}
