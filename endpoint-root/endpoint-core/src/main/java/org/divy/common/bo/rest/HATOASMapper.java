package org.divy.common.bo.rest;

import org.divy.common.bo.endpoint.hatoas.Representation;
import org.divy.common.bo.mapper.BOMapper;
import org.divy.common.bo.repository.BusinessObject;

public interface HATOASMapper<E extends BusinessObject<?>, R extends Representation>
        extends BOMapper<E, R> {

}
