package org.divy.common.rest.impl;


import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.endpoint.hypermedia.AbstractRepresentation;
import org.divy.common.bo.mapper.defaults.AdvanceBOMapper;
import org.divy.common.rest.HATEOASMapper;
import org.divy.common.rest.LinkBuilderFactoryImpl;

import javax.inject.Inject;

public abstract class AbstractHATEOASMapper<E extends IBusinessObject<I>,
        REPRESENTATION extends AbstractRepresentation
        , I>
        extends AdvanceBOMapper<E, REPRESENTATION>
        implements HATEOASMapper<E, REPRESENTATION> {

	@Inject
	private LinkBuilderFactoryImpl linkBuilderFactory;

    public AbstractHATEOASMapper(Class<E> businessObjectType, Class<REPRESENTATION> otherObjectType) {
        super(businessObjectType, otherObjectType);
	}

	@Override
    protected E doMapToBO(REPRESENTATION sourceData,
                          E businessObject) {
        E entity = super.doMapToBO(sourceData, businessObject);
        doReadLinks(sourceData, businessObject);
		doReadAssociations(sourceData, businessObject);
		return entity;
	}

	@Override
	protected REPRESENTATION doMapFromBO(
            E businessObject, REPRESENTATION representation) {
        super.doMapFromBO(businessObject, representation);
		doFillLinks(representation, businessObject);
		doFillAssociations(representation, businessObject);
		return representation;
	}


	public LinkBuilderFactoryImpl getLinkBuilderFactory() {
		return linkBuilderFactory;
	}


	public void setLinkBuilderFactory(LinkBuilderFactoryImpl linkBuilderFactory) {
		this.linkBuilderFactory = linkBuilderFactory;
	}

    abstract protected void doFillLinks(REPRESENTATION representation, E businessObject);

    abstract protected void doFillAssociations(REPRESENTATION representation, E businessObject);

    abstract protected void doReadLinks(REPRESENTATION representation, E businessObject);

    abstract protected void doReadAssociations(REPRESENTATION representation, E businessObject);
}
