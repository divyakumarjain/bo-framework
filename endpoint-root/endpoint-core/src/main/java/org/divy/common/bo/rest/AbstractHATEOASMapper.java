package org.divy.common.bo.rest;


import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.endpoint.hypermedia.AbstractRepresentation;
import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.metadata.MetaDataProvider;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class AbstractHATEOASMapper <E extends IBusinessObject<UUID>, R extends AbstractRepresentation<UUID>>
        implements HATEOASMapper<E, R> {

    private final MetaDataProvider metaDataProvider;
    private final LinkBuilderFactory linkBuilderFactory;
    private final Class<R> representationType;
    private final Class<E> businessObjectType;

    private final IBOMapper<E, Map<String, Object>> keyValuePairMapper;

    public AbstractHATEOASMapper(Class<E> businessObjectType
                                 , Class<R> representationType
                                 , IBOMapper<E, Map<String, Object>> keyValuePairMapper
                                 , LinkBuilderFactory linkBuilderFactory
                                 , MetaDataProvider metaDataProvider) {
        this.businessObjectType = businessObjectType;
        this.representationType = representationType;
        this.linkBuilderFactory = linkBuilderFactory;
        this.metaDataProvider = metaDataProvider;
        this.keyValuePairMapper = keyValuePairMapper;
    }

    public LinkBuilderFactory getLinkBuilderFactory() {
        return linkBuilderFactory;
    }

    private IBOMapper<E, Map<String, Object>> getKeyValuePairMapper() {
        return keyValuePairMapper;
    }

    @Override
    public Collection<R> createRepresentationFromBO(Collection<E> boList) {
        return boList.stream().map(this::createRepresentationFromBO).collect(Collectors.toList());
    }



    @Override
    public Collection<E> createBOFromRepresentation(Collection<R> representations) {
        return representations.stream().map(this::createBOFromRepresentation)
                .collect(Collectors.toList());
    }

    @Override
    public R createRepresentationFromBO(E businessObject) {
        R representation = createRepresentationInstance();
        representation.setId(businessObject.identity());
        mapFromBO(businessObject, representation.getData());
        doFillLinks(representation, businessObject);
        doFillAssociations(representation, businessObject);
        return representation;
    }

    @Override
    public E createBOFromRepresentation(R representation) {
        final E businessObject = createBO(representation.getData());
        doReadAssociations(representation, businessObject);
        doReadLinks(representation, businessObject);
        return businessObject;
    }

    private R createRepresentationInstance() {
        try {
            return this.representationType.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public E createBO(Map<String, Object> stringObjectMap) {
        return this.getKeyValuePairMapper().createBO(stringObjectMap);
    }

    @Override
    public E mapToBO(Map<String, Object> stringObjectMap, E bo) {
        return this.getKeyValuePairMapper().mapToBO(stringObjectMap,bo);
    }

    @Override
    public Map<String, Object> createFromBO(E e) {
        return this.getKeyValuePairMapper().createFromBO(e);
    }

    @Override
    public Map<String, Object> mapFromBO(E e, Map<String, Object> stringObjectMap) {
        return this.getKeyValuePairMapper().mapFromBO(e, stringObjectMap);
    }

    @Override
    public Collection<E> createBO(Collection<Map<String, Object>> collection) {
        return this.getKeyValuePairMapper().createBO(collection);
    }

    @Override
    public Collection<Map<String, Object>> createFromBO(Collection<E> collection) {
        return this.getKeyValuePairMapper().createFromBO(collection);
    }

    protected abstract void doFillLinks(R representation, E businessObject);

    protected abstract void doFillAssociations(R representation, E businessObject);

    protected abstract void doReadLinks(R representation, E businessObject);

    protected abstract void doReadAssociations(R representation, E businessObject);

    MetaDataProvider getMetaDataProvider() {
        return metaDataProvider;
    }
}
