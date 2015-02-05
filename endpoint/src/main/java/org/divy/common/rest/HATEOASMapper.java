package org.divy.common.rest;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.endpoint.hypermedia.AbstractRepresentation;
import org.divy.common.bo.mapper.IBOMapper;

public interface HATEOASMapper<ENTITY extends IBusinessObject<?>,
        REPRESENTATION extends AbstractRepresentation>
        extends IBOMapper<ENTITY, REPRESENTATION>{

}
