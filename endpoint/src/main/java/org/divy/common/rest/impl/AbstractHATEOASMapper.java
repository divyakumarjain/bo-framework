package org.divy.common.rest.impl;


import javax.inject.Inject;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.endpoint.hypermedia.AbstractRepresentation;
import org.divy.common.bo.mapper.AbstractBOMapper;
import org.divy.common.rest.HATEOASMapper;
import org.divy.common.rest.LinkBuilderFactory;

public abstract class AbstractHATEOASMapper<ENTITY extends IBusinessObject<ID>,
		REPRESENTATION extends AbstractRepresentation
		,ID>
	extends AbstractBOMapper<ENTITY, REPRESENTATION>
	implements HATEOASMapper<ENTITY, REPRESENTATION> {

	@Inject
	protected LinkBuilderFactory linkBuilderFactory;

	public AbstractHATEOASMapper(Class<ENTITY> businessObjectType, Class<REPRESENTATION> otherObjectType) {
		super(businessObjectType, otherObjectType);
	}


	@Override
	protected ENTITY doMapToBO(REPRESENTATION sourceData,
			ENTITY businessObject) {
		ENTITY entity = super.doMapToBO(sourceData, businessObject);
		doReadLinks(sourceData, businessObject);
		doReadAssociations(sourceData, businessObject);
		return entity;
	}

	@Override
	protected REPRESENTATION doMapFromBO(
			ENTITY businessObject, REPRESENTATION representation) {
		super.doMapFromBO(businessObject, representation);
		doFillLinks(representation, businessObject);
		doFillAssociations(representation, businessObject);
		return representation;
	}

	abstract protected void doFillLinks(REPRESENTATION representation, ENTITY businessObject);
	abstract protected void doFillAssociations(REPRESENTATION representation, ENTITY businessObject);

	abstract protected void doReadLinks(REPRESENTATION representation, ENTITY businessObject);
	abstract protected void doReadAssociations(REPRESENTATION representation, ENTITY businessObject);
}
