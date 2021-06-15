package org.divy.common.bo.endpoint.hatoas.association;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.endpoint.hatoas.association.builder.AssociationBuilder;
import org.divy.common.bo.mapper.builder.MapperBuilder;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.rest.LinkBuilderFactory;

import java.util.*;

public abstract class AbstractAssociationsHandler<T extends BusinessObject<I>,I, L> implements AssociationsHandler<T, I, L>
{
    protected final Class<T>                                         source;
    protected final MetaDataProvider                                 metaDataProvider;
    protected final MapperBuilder                                    mapperBuilder;
    protected final LinkBuilderFactory<L>                            linkBuilderFactory;
    private ArrayList<Association<T, I, L>> associations = null;

    protected AbstractAssociationsHandler(MetaDataProvider metaDataProvider, Class<T> source, MapperBuilder mapperBuilder, LinkBuilderFactory<L> linkBuilderFactory)
    {
        this.metaDataProvider = metaDataProvider;
        this.source = source;
        this.mapperBuilder = mapperBuilder;
        this.linkBuilderFactory = linkBuilderFactory;
    }

    @Override
    public Optional<Association<T, I, L>> getAssociation(String relation) {
        return getAssociations().stream().filter(it->relation.equals(it.getName())).findAny();
    }

    @Override
    public List<Association<T, I, L>> getAssociations() {
        if(associations==null){
            associations = new ArrayList<>();
            doBuildAssociations();
        }
        return associations;
    }

    protected abstract void doBuildAssociations();

    protected AssociationBuilder<T, I, L> association() {
        var association = new AssociationBuilder<T, I, L>();
        associations.add(association);
        return association;
    }
}
