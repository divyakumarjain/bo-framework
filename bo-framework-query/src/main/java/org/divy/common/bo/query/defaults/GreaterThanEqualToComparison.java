package org.divy.common.bo.query.defaults;

import org.divy.common.bo.query.IGreaterThanEqualToComparison;


public class GreaterThanEqualToComparison<ATTRIBUTE> implements
		IGreaterThanEqualToComparison<ATTRIBUTE> {

	private ATTRIBUTE value;
	
	public GreaterThanEqualToComparison() {
		super();
	}
	
	public GreaterThanEqualToComparison(ATTRIBUTE value) {
		super();
		this.value = value;
	}

	public void setValue(ATTRIBUTE value) {
		this.value = value;
	}

	@Override
	public ATTRIBUTE getValue() {
		return value;
	}

}
