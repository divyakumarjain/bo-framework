package org.divy.common.bo.rest;


import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.endpoint.hatoas.Representation;
import org.divy.common.bo.mapper.BOMapper;
import org.divy.common.bo.metadata.MetaDataProvider;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class AbstractHATOASMapper<E extends BusinessObject<UUID>
        , R extends Representation<UUID, Map<String, Object>, L>
        , L>
        implements HATOASMapper<E, R>
{

    private final MetaDataProvider metaDataProvider;
    private final LinkBuilderFactory<L> linkBuilderFactory;
    private final Class<R> representationType;
    private final BOMapper<E, Map<String, Object>> keyValuePairMapper;

    public AbstractHATOASMapper(Class<R> representationType
                                 , BOMapper<E, Map<String, Object>> keyValuePairMapper
                                 , LinkBuilderFactory<L> linkBuilderFactory
                                 , MetaDataProvider metaDataProvider) {

        this.representationType = representationType;
        this.keyValuePairMapper = keyValuePairMapper;
        this.linkBuilderFactory = linkBuilderFactory;
        this.metaDataProvider = metaDataProvider;
    }

    protected LinkBuilderFactory<L> getLinkBuilderFactory() {
        return linkBuilderFactory;
    }

    private BOMapper<E, Map<String, Object>> getKeyValuePairMapper() {
        return keyValuePairMapper;
    }

    @Override
    public R createRepresentationFromBO(E businessObject) {
        R representation = createRepresentationInstance();
        representation.setId(businessObject.identity());
        representation._type(businessObject._type());
        mapFromBO(businessObject, representation.getData());
        doFillLinks(representation, businessObject);
        doFillAssociations(representation, businessObject);
        return representation;
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
    public E createBOFromRepresentation(R representation) {
        final E businessObject = createBO(representation.getData());
        doReadAssociations(representation, businessObject);
        doReadLinks(representation, businessObject);
        return businessObject;
    }

    private R createRepresentationInstance() {
        try {
            return this.representationType.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e ) {
            throw new IllegalArgumentException("Representation _type " + representationType.getName() + " should have public default constructor", e);
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

    protected MetaDataProvider getMetaDataProvider() {
        return metaDataProvider;
    }
}
