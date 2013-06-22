package org.divy.common.bo.business.impl;

import org.divy.common.bo.IBORepository;
import org.divy.common.bo.IBusinessObject;

import com.google.inject.Inject;

public class DefaultBOManager<ENTITY extends IBusinessObject<ID>, ID> extends AbstractBOManager<ENTITY, ID> {

	@Inject
	public DefaultBOManager(IBORepository<ENTITY, ID> repository) {
		super(repository);
	}
}
