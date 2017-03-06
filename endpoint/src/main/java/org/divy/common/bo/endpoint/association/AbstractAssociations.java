package org.divy.common.bo.endpoint.association;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAssociations<T> {

    private final Class<T> source;

    private ArrayList<Association<T>> associations = null;

    public AbstractAssociations(Class<T> source){
        this.source = source;
    }

    public Association<T> getAssociation(String relation) {
        
        for(Association<T> association : getAssociations()) {
            if(association.getName().equals(relation)) {
                return association;
            }
        }
        throw new IllegalArgumentException("Association " + relation + " not found for source " + source.getName());
    }

    public List<Association<T>> getAssociations() {
        if(associations==null){
            associations = new ArrayList<>();
            doBuildAssociations();
        }
        return associations;
    }

    protected abstract void doBuildAssociations();

    protected Association<T> association() {
        Association<T> association = new Association<>();
        associations.add(association);
        return association;
    }
}
