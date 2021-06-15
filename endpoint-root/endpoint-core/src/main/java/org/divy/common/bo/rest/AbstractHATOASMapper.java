package org.divy.common.bo.rest;


import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.endpoint.hatoas.Representation;
import org.divy.common.bo.mapper.BOMapper;
import org.divy.common.bo.metadata.MetaDataProvider;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
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

    protected AbstractHATOASMapper(Class<R> representationType
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

    private R createRepresentationInstance() {
        try {
            return this.representationType.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e ) {
            throw new IllegalArgumentException("Representation _type " + representationType.getName() + " should have public default constructor", e);
        }
    }

    @Override
    public E createBO(R representation) {
        final var businessObject = this.keyValuePairMapper.createBO( representation.getData() );
        doReadAssociations(representation, businessObject);
        doReadLinks(representation, businessObject);
        return businessObject;
    }

    @Override
    public E mapToBO(R representation, E bo) {
        return this.getKeyValuePairMapper().mapToBO(representation.getData(),bo);
    }

    @Override
    public R createFromBO(E businessObject) {
        var representation = createRepresentationInstance();
        representation.setId(businessObject.identity());
        representation._type(businessObject._type());
        mapFromBO(businessObject, representation);
        doFillLinks(representation, businessObject);
        doFillAssociations(representation, businessObject);
        return representation;
    }

    @Override
    public R mapFromBO(E e, R representation) {
        if(representation.getData() == null) {
            representation.setData( new HashMap<>( ) );
        }
        this.getKeyValuePairMapper().mapFromBO(e, representation.getData());
        return representation;
    }

    @Override
    public Collection<E> createBO( Collection<R> representations) {
        return representations.stream().map(this::createBO)
              .collect(Collectors.toList());
    }

    protected abstract void doFillLinks(R representation, E businessObject);

    protected abstract void doFillAssociations(R representation, E businessObject);

    protected abstract void doReadLinks(R representation, E businessObject);

    protected abstract void doReadAssociations(R representation, E businessObject);

    protected MetaDataProvider getMetaDataProvider() {
        return metaDataProvider;
    }

    @Override
    public Collection<R> createFromBO( Collection<E> businessObjects )
    {
        return businessObjects.stream().map( this::createFromBO )
              .collect( Collectors.toList());
    }
}
