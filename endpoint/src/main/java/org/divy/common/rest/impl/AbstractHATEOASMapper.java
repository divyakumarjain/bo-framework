package org.divy.common.rest.impl;


import org.divy.common.bo.database.AbstractBusinessObject;
import org.divy.common.bo.endpoint.hypermedia.AbstractRepresentation;
import org.divy.common.bo.mapper.defaults.AdvanceBOMapper;
import org.divy.common.rest.HATEOASMapper;
import org.divy.common.rest.LinkBuilderFactory;
import org.dozer.loader.api.FieldsMappingOptions;
import org.dozer.loader.api.TypeMappingBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class AbstractHATEOASMapper <E extends AbstractBusinessObject,
		R extends AbstractRepresentation<UUID>>
		extends AdvanceBOMapper<E, Map<String, Object>>
		implements HATEOASMapper<E, R> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractHATEOASMapper.class);
	private LinkBuilderFactory linkBuilderFactory;

	Class<R> representationType;

	public AbstractHATEOASMapper(Class<E> businessObjectType, Class<R> representationType,
								 LinkBuilderFactory linkBuilderFactory) {
		super(businessObjectType, (Class)Map.class);
		this.linkBuilderFactory = linkBuilderFactory;
		this.representationType = representationType;
	}

	@Override
	protected void configureMapping(TypeMappingBuilder mapper) {
		super.configureMapping(mapper);
		mapper.fields("lastUpdateTimestamp","lastUpdateTimestamp", FieldsMappingOptions.oneWay());
		mapper.fields("createTimestamp","createTimestamp", FieldsMappingOptions.oneWay());
		mapper.fields("uuid","uuid", FieldsMappingOptions.oneWay());
	}

	public LinkBuilderFactory getLinkBuilderFactory() {
		return linkBuilderFactory;
	}


	public void setLinkBuilderFactory(LinkBuilderFactory linkBuilderFactory) {
		this.linkBuilderFactory = linkBuilderFactory;
	}


	@Override
	public Collection<R> createRepresentationFromBO(Collection<E> boList) {
		return boList.stream().map(bo-> createRepresentationFromBO(bo)).collect(Collectors.toList());
	}



	@Override
	public Collection<E> createBOFromRepresentation(Collection<R> representations) {
		return representations.stream().map(representation-> createBOFromRepresentation(representation))
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
		return createBO(representation.getData());
	}

	private R createRepresentationInstance() {
		try {
			return this.representationType.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	abstract protected void doFillLinks(R representation, E businessObject);

	abstract protected void doFillAssociations(R representation, E businessObject);

	abstract protected void doReadLinks(R representation, E businessObject);

	abstract protected void doReadAssociations(R representation, E businessObject);
}
